# AOP 的原理.md


## 使用 
```java
@Configuration
@Import({SchoolService.class, SchoolRepository.class, LogAdvices.class})
@EnableAspectJAutoProxy // 启动 Spring AOP 动态代理
public class AspectjDemo extends BaseDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AspectjDemo.class);
        printAllBeanName(applicationContext);


        // 使用 LogAdvices 切面 对 SchoolService#getSchoolName() 方法进行增强

        SchoolService schoolService = applicationContext.getBean(SchoolService.class);
        String schoolName = schoolService.getSchoolName();

        System.out.println("---> schoolName = " + schoolName);
        
    }


}
```

```java
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
```

## 源码

看源码先从 注解 `@EnableAspectJAutoProxy` 看起:

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AspectJAutoProxyRegistrar.class)
public @interface EnableAspectJAutoProxy {
}
```



# AOP 编程

说到 AOP 就离不开动态代理,说到动态代理就离不开静态代理,

先说静态代理的缺点:

1. 静态代理类文件数量过多,不利于系统管理,
2. 在大量使用的时候维护性性差,修改复杂.

Spring 动态代理





