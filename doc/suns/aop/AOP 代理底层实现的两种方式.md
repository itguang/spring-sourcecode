# AOP



## 底层实现

### 1. JDK 动态代理（基于接口）

![image-20201004161959464](https://itguang.oss-cn-beijing.aliyuncs.com/20201004161959.png)

![](https://itguang.oss-cn-beijing.aliyuncs.com/20201004162107.png)



![image-20201004164137112](https://itguang.oss-cn-beijing.aliyuncs.com/20201004164137.png)

### 2.CGLib（基于父子继承）

![image-20201004164902940](https://itguang.oss-cn-beijing.aliyuncs.com/20201004164903.png)

![image-20201004171210004](https://itguang.oss-cn-beijing.aliyuncs.com/20201004171210.png)



## 动态代理总结



![image-20201004171528261](https://itguang.oss-cn-beijing.aliyuncs.com/20201004171528.png)

## 再看 BeanPostProcess

Spring 是怎样为我们创建代理对象的嫩？

答案就是 BeanPostProcessor 。

