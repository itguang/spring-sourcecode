# Aspectj 与 Spring AOP 比较

参考资料: [Aspectj 与 Spring AOP 比较](https://www.jianshu.com/p/872d3dbdc2ca) 

参考资料: [面试官：什么是 AOP？Spring AOP 和 AspectJ 的区别是什么？](https://www.jianshu.com/p/9b107da30466)

## AOP

首先看一下 AOP 的概念,AOP 是一种编程范式,是 OOP 的一个补充.
旨在通过允许横切关注点的分离，提高模块化。AOP 提供切面来将跨越对象关注点模块化。
**实现 AOP 的关键在于 AOP 框架为我们自动创建代理.**

而 AOP 代理又可分为 **静态代理** 和 **动态代理** 两大类.

**静态代理**: 

> 其中静态代理是指使用 AOP 框架提供的命令进行编译，从而在编译阶段就可生成 AOP 代理类，因此也称为编译时增强；

**动态代理**: 

> 而动态代理则在运行时借助于 JDK 动态代理、CGLIB 等在内存中 “临时” 生成 AOP 动态代理类，因此也被称为运行时增强。


### Spring AOP:

* 基于 JDK 动态代理,或者 cglib 实现

> Spring AOP 旨在提供一个基于 IoC 容器的 简单的 AOP 实现.
> 用来解决程序员面临的常见问题,它不不打算作为一个完成的解决方案,它只能应用于 Spring IoC 容器管理的 bean




### Aspectj:
* 完成的 AOP 编程框架,支持: 

> Aspectj 旨在提供一个完整的 AOP 解决方案.
> 比 Spring AOP 要更复杂,是Spring AOP 的一个超集.Aspectj 可以在所有对象域中使用.


## Weaving 

### Aspectj

**AspectJ 使用三种不同类型的 Weaving:**

* **编译时** Weaving: AspectJ 编译器作为输入我们的方面的源代码和我们的应用，并产生一个织入类文件作为输出；
* **编译后** Weaving: 这也称为二进制织入。它是用来织入现有的类文件和 JAR 文件与我们的方面；
* **加载时** Weaving: 这就像前二进制织入，不同的是织入被推迟，直到类加载程序加载类文件到 JVM。


### Spring AOP

Spring AOP 不同于上面任何一种，Spring AOP 利用运行时织入。
使用运行时编织，这些方面在使用目标对象的代理执行应用程序时被编织 - 使用 JDK 动态代理或 CGLIB 代理


## spring-aop 和 spring-aspects 包的区别?



