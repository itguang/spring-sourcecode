package com.itguang.aop;

import org.aspectj.lang.annotation.*;

/**
 * 日志切面
 */
@Aspect
public class LogAdvices {

    /**
     * 定义切点,可以在内部引用们也可以在外部引用
     */
    @Pointcut("execution( public String com.itguang.service.SchoolService.*(..))")
    public void pointcut(){

    }

    // 在内部引用切点
    @Before("pointcut()")
    public void before(){
        System.out.println("------> AOP before ...");
    }


    public void around(){
    }

    // 在外部可以这样引用这个切点
    @AfterReturning("com.itguang.aop.LogAdvices.pointcut()")
    public void after(){
        System.out.println("------> AOP after ...");

    }


    public void exception(){

    }





}
