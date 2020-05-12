package com.jeasonchan.dailyexercise.leetcode.并查集专项练习.奇偶数分类;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@Component
@Aspect
public class AspectLog {


    @Pointcut("@annotation(Log)")
    public void log() {

    }


    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("doBefore line 22");

        //获取方法的入参
        System.out.println("joinPoint.getArgs()" + Arrays.toString(joinPoint.getArgs()));

        Signature signature = joinPoint.getSignature();

        /*根据源码注解，想获得特定的签名信息，需要转型为相应的实例
         * <p>More detailed information about a specific kind of signature can
         * be obtained by casting this <code>Signature</code> object into one
         * of its more specific sub-types available in
         * <code>org.aspectj.lang.reflect</code>.
         */

        MethodSignature methodSignature = (MethodSignature) signature;

        //通过MethodSignature 获取 方法对象 实例
        Method method = methodSignature.getMethod();

        Log log = method.getDeclaredAnnotation(Log.class);

        System.out.println("注解上的入参是：" + log.extraValue());


    }


    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("doAround line 57");

        Object[] originArgs = proceedingJoinPoint.getArgs();

        System.out.println("proceedingJoinPoint.getArgs():" + originArgs);

        Object originResult = proceedingJoinPoint.proceed();

        ((Map<String, Object>) originResult).put("故意插入值", "23333");

        //此处的返回值会被当做原方法的返回值
        return originResult;


    }


}
