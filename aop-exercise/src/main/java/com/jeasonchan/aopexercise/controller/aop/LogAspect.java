package com.jeasonchan.aopexercise.controller.aop;


import com.jeasonchan.aopexercise.controller.Annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

//要使类称为切面处理类，必须满足两点：1、能注入到IOC中；2、被标注为aspect，成为切面类
@Component
@Aspect
public class LogAspect {

    //切入点是这个注解，但实际还是被注解的方法
    @Pointcut("@annotation(com.jeasonchan.aopexercise.controller.Annotation.Log)")
    public void pointCut() {
        //此处用这个方法代表Log注解的使用处
        System.out.println("line 18");
        //这一行打印并没有出现，并且IDEA提示，pointcut方法体应该为空
    }


    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("line 26:" + joinPoint);
        //todo 为什么切点写明的是注解类型。joinpoint却是下面的被注解注释的方法？？？
        //line 26:execution(Map com.jeasonchan.aopexercise.controller.ActionAndObject.logSth(String,String,List))

        //todo 这个参数是哪里传进来的？？？？
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        System.out.println(request.getServletContext());

        handleJoinPoint(joinPoint);
    }


    //返回后通知，可以获取方法的返回值
    //使用前要先在注解中声明，才可以在返回后通知的方法中使用
    @AfterReturning(value = "pointCut()", returning = "controllerResult")
    public void doAfter(JoinPoint joinpoint, Object controllerResult) {
        System.out.println("line 33:" + joinpoint);
        //line 33:execution(Map com.jeasonchan.aopexercise.controller.ActionAndObject.logSth(String,String,List))

        handleJoinPoint(joinpoint);

        System.out.println("rest接口的返回值是：" + controllerResult);


    }


    private void handleJoinPoint(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);
        System.out.println(log.restApiName() + "  " + log.detail());


        System.out.println("=================================");


    }


}

