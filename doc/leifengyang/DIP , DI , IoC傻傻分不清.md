# DIP , DI , IoC 

![](https://itguang.oss-cn-beijing.aliyuncs.com/20200814172530.jpg)



* **依赖倒置原则**(   **Dependency Inversion Principle**   )

* **控制反转( Inversion of Control )**

* **依赖注入（Dependency Injection）**

《敏捷软件开发》:

>  a.高层模块不应该依赖于底层模块，二者都应该依赖于抽象 ;
>
> b.抽象不应该依赖于细节，细节应该依赖于抽象。

可见，

**依赖倒置原则**的本质是 **依赖抽象**。

**依赖注入**的本质是**依赖容器**。

换句话说，如果如果 java 没有接口，多态，**依赖倒置**就无从谈起。

而**依赖注入**依然可以存在，只要有一个注册表定义 bean ，利用反射来实例化并装配 bean ，有个容器容纳它们即可。



IoC主要的实现方式有两种：依赖查找，依赖注入。





## Design Principle vs Design Pattern

In software engineering, design principle and design pattern are not the same.

## Design Principle

Design principles provide high level guidelines to design better software applications. They do not provide implementation guidelines and are not bound to any programming language. The SOLID (SRP, OCP, LSP, ISP, DIP) principles are one of the most popular sets of design principles.

For example, the Single Responsibility Principle (SRP) suggests that a class should have only one reason to change. This is a high-level statement which we can keep in mind while designing or creating classes for our application. SRP does not provide specific implementation steps but it's up to you how you implement SRP in your application.

## Design Pattern

Design Pattern provides low-level solutions related to implementation, of commonly occurring object-oriented problems. In other words, design pattern suggests a specific implementation for the specific object-oriented programming problem. For example, if you want to create a class that can only have one object at a time, then you can use the Singleton design pattern which suggests the best way to create a class that can only have one object.

Design patterns are tested by others and are safe to follow, e.g. Gang of Four patterns: Abstract Factory, Factory, Singleton, Command, etc.





## IoC

控制反转（IoC）是一种设计原则（尽管有些人将其称为模式）。顾名思义，它用于反转面向对象设计中的各种控件，以实现松耦合。IoC 原理有助于设计松散耦合的类，使它们可测试，可维护和可扩展。



## DIP

DIP 是 [Robert Martin](https://en.wikipedia.org/wiki/Robert_Cecil_Martin)（又名 Bob 大叔） 发明的 SOLID 面向对象原理之一。

定义： 

1. 高级模块不应依赖于低级模块。两者都应取决于抽象。
2. 抽象不应依赖细节。细节应取决于抽象。



## DI

依赖注入（DI）是用于实现IoC的设计模式。它允许在类之外创建依赖对象，并通过不同方式将这些对象提供给类。使用DI，我们将依赖对象的创建和绑定移到依赖它们的类之外。



依赖注入模式涉及3种类型的类。

1. **客户端类：**客户端类（从属类）是依赖于服务类的类
2. **服务类：**服务类（相关性）是为客户端类提供服务的类。
3. **注入器类：**注入器类将服务类对象注入到客户端类中。



![](https://itguang.oss-cn-beijing.aliyuncs.com/20200814190730.png)





如上图所示，注入器类创建服务类的对象，并将该对象注入客户端对象。这样，DI模式将创建服务类的对象的职责与客户类分开。

其中，注入类可以通过三种常用的方式注入依赖项，分别是：构造函数注入；属性注入；方法注入。

**构造函数注入：**在构造函数注入中，注入器通过客户端类构造函数提供服务（依赖项）。

**属性注入：**在属性注入（又称“ Setter注入”）中，注入器通过客户端类的公共属性提供依赖项。

**方法注入：**在这种类型的注入中，客户端类实现一个接口，该接口声明提供依赖项的方法，并且注入   器使用此接口向客户端类提供依赖项。



## IoC Container 

IoC容器（又名DI容器）是用于实现自动依赖项注入的框架。它管理对象的创建及其生命周期，还向类注入依赖项。

IoC容器创建指定类的对象，并在运行时通过构造函数，属性或方法注入所有依赖项对象，并在适当的时间对其进行处理。这样做是为了我们不必手动创建和管理对象。



所有容器必须为接下来的DI生命周期提供简便的支持。

- **注册（Register）：**容器在遇到特定类型时必须知道要实例化哪个依赖项。此过程称为注册。基本上，它必须包含某种注册类型映射的方法。
- **解析（Resolve）：**使用IoC容器时，我们不需要手动创建对象。容器为我们做到了。这称为分辨率。容器必须包含一些方法来解析指定的类型。容器将创建指定类型的对象，并注入所需的依赖项（如果有）并返回该对象。
- **处置（Dispose）：**容器必须管理依赖对象的生存期。大多数IoC容器包括不同的生命周期管理器来管理对象的生命周期并进行处置。



