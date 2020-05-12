package com.jeasonchan.demologaop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
@Aspect
public class LogAspect {
    private static final String MyArg = "request-start";


    //该种方式，切点为Controller类下面的所有public方法
    @Pointcut("execution(public * com.jeasonchan.demologaop.Controller.*(..))")
//该种方式，切点为 给该注解注释了的方法
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void log() {

    }


    @Before("log()")
    //JoinPoint包含了被切的方法的  相关的程序元素信息（类、方法、注解）和运行时信息（传入的参数列表）
    public void doBefore(JoinPoint joinPoint) {

        //获取网络请求对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//该方法能返回该线程的传入参 数
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;

        HttpServletRequest httpServletRequest = Objects.requireNonNull(servletRequestAttributes).getRequest();
        System.out.println("httpServletRequest.getRequestURL()  " + httpServletRequest.getRequestURL());
        System.out.println("httpServletRequest.getRemoteUser()  " + httpServletRequest.getRemoteUser());
        System.out.println("httpServletRequest.getRemoteAddr()  " + httpServletRequest.getRemoteAddr());
        System.out.println("joinPoint.getArgs()  " + Arrays.toString(joinPoint.getArgs()));

        //获取处理请求的方法信息
        System.out.println("joinPoint.getSignature().getDeclaringTypeName()  " + joinPoint.getSignature().getDeclaringTypeName());

        //获取httpServletRequest中的参数Map
        System.out.println("httpServletRequest.getParameterMap()  " + httpServletRequest.getParameterMap().keySet());  //只有requestParam的参数

        //手动“篡改”请求中的参数信息
        Long start = System.currentTimeMillis();
        httpServletRequest.setAttribute(MyArg, start);


    }

    @Around("log()")
    @SuppressWarnings("unchecked")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();

        System.out.println("Object result = proceedingJoinPoint.proceed():" + result);

        ((Map<String, Object>) result).put("AroundInject", "2333333");

        return result;
    }


    @After("log()")
    //用不到Joinpoint时可以不写
    public void doAfter() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        Long start = (Long) request.getAttribute(MyArg);
        Long end = System.currentTimeMillis();
        System.out.println("【请求耗时】：毫秒:" + (end - start));

        String header = request.getHeader("User-Agent");
        System.out.println("User-Agent:" + header);
    }
}

/*
最终的输出是：
httpServletRequest.getRequestURL()  http://localhost:8080/demo/jeasonchan
httpServletRequest.getRemoteUser()  null
httpServletRequest.getRemoteAddr()  0:0:0:0:0:0:0:1
joinPoint.getArgs()  [jeasonchan, 18, [中兴]]     //按照方法的入参顺序，按顺序列出
joinPoint.getSignature().getDeclaringTypeName()  com.jeasonchan.demologaop.Controller
httpServletRequest.getParameterMap()  [age]     //只有requestParam的参数
Object result = proceedingJoinPoint.proceed():{RequestParam=18, PathVariable=jeasonchan, RequestBody=[中兴]}
【请求耗时】：毫秒:514
User-Agent:PostmanRuntime/7.23.0
 */
