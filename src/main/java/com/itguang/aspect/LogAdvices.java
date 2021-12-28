package com.itguang.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.web.bind.annotation.RestController;

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

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void pointcut2() {
    }

    // 在内部引用切点

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 目标对象
        Object target = joinPoint.getTarget();
        // 代理对象
        Object aThis = joinPoint.getThis();
        // 目标方法参数
        Object[] args = joinPoint.getArgs();
        // 目标方法的签名信息
        Signature signature = joinPoint.getSignature();


        // doSomething
    }

    // AfterThrowing 绑定异常信息
    @AfterThrowing(value = "pointcut()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,RuntimeException e) {
        // doSomething
    }


    // AfterReturning 绑定返回值
    @AfterReturning(value = "pointcut()",returning = "retValue")
    public void afterThrowing(JoinPoint joinPoint,String retValue) {
        // doSomething
    }

    // After(Finally) 绑定返回值: 更多时候我们使用 After（Finally）来处理网络连接的释放等。
    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        // doSomething
    }

    // Around ，注意第一个参数较之前几个有变化，必须是 ProceedingJoinPoint 类型,且需要 通过 调用其 proceed() 方法继续目标方法的执行
    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) {
        try {
            System.out.println("start...");
            joinPoint.proceed();
            System.out.println("end...");
        } catch (Throwable e) {
            System.out.println("Throwable...");
        }
    }

}
