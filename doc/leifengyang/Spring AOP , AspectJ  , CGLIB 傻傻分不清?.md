# Spring AOP , AspectJ  , CGLIB 傻傻分不清?

* **AOP**

  > AOP(Aspect Orient Programming) 作为面向对象的一种补充，广泛用于处理具有横切性质的系统级服务，如 事务，安全检查，缓存，对象池管理等。
  >
  > AOP 实现的关键就在于 AOP 框架自动创建代理对象，AOP 代理可分为 静态代理 和 动态代理 两大类，静态代理在编译阶段就可以生成代理类，因此也称为 编译时增强；动态代理 在运行时 借助 JDK 动态代理或者 CGLIB 等在内存中临时生成代理类，因此也不被称为运行时增强

* **Spring AOP:** 

  > Spring 提供的 AOP 框架，底层使用 JDK 动态代理或者 CGLIB 来实现，支持了 Aspectj 的注解标签

* **CGLIB:**

  > CGLIB 是一个功能强大，高性能的代码生成包，它可以为没有实现接口的类提供代理，对 JDK 基于接口的动态代理做了很好的补充。

* **AspectJ:**

  > AspectJ 是一个 AOP 框架，是事实上的 AOP 标准。



一句话概括:

Spring AOP 最终使用的是 JDK 或者 CGLIB 来实现的, 只是 Spring AOP 支持了 Aspectj 的注解标签.

