package com.itguang.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 日志切面
 */
@Aspect
public class LogAdvices {

    /**
     * 定义切点,可以在内部引用们也可以在外部引用
     */
    @Pointcut("execution( public String com.itguang.service.SchoolService.*(..))")
    public void pointcut() {

    }

    // 在内部引用切点
    @Before("pointcut()")
    public void before() {
        System.out.println("------> AOP before ...");
    }


    /**
     * 环绕通知
     */
//    @Around("execution(* *(..))")
//    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//
//        // 调用切入点方法
//        Object ret = proceedingJoinPoint.proceed();
//
//        return ret;
//    }

    // 在外部可以这样引用这个切点
    @AfterReturning("com.itguang.aspect.LogAdvices.pointcut()")
    public void after() {
        System.out.println("------> AOP after ...");

    }


    public void exception() {

    }


}
