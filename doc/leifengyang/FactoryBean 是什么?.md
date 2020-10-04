# FactoryBean



FactoryBean 是 Spring 提供的用来创建复杂 Spring Bean 的接口。

那什么是复杂对象（Bean）呢？复杂对象（Bean）就是那些不能简单通过构造方法实例化的对象



![image-20200915225742975](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200915225742975.png)



![image-20200916213511184](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200916213511184.png)



如果 bean 的类型为 FactoryBean 接口类型，那么在工厂 getBean 时，其实获取的是 FactoryBean.getObject() 方法。因此，FactoryBean 接口有延迟初始化的效果。

![image-20200916214459604](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200916214459604.png)





## 接口加反射 ，什么都能做 ！



FactoryBean 总结：Spring 中用于创建复杂 Bean 的一种方式，也是Spring原生提供的，其他框架与Spring整合的时候大量用到 FactoryBean。



## Spring 工厂创建复杂对象的三种方式：

### 1.FactoryBean 接口

![image-20200915230526248](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200915230526248.png)

![image-20200915230601851](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200915230601851.png)

### 2.实例工厂

相对于 Factorybean 的好处：

```mark
1. 避免 Spring 框架的侵入
2. 整合遗留代码
```

![image-20200916224415227](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200916224415227.png)

### 3.静态工厂 

![image-20200916224549060](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200916224549060.png)

## Spring 创建对象

![image-20200916224723972](https://itguang.oss-cn-beijing.aliyuncs.com/image-20200916224723972.png)

