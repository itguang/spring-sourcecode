# 关于 Spring 的自动注入(自动装配): 字段,方法,构造器

先来看个例子:

```java
@Component
@Data
public class Boss {

    // 构造函数注入,由于 Spring 在初始化 Boss 这个 Bean 时,会调用 这个带参数的构造器,
    // 那么Spring 就会 从 IoC 容器中找到 构造器中的参数 Car 把这个 car bean 实例注入进来
    // 所以,在有参构造器只有一个参数的情况下,这里不用添加 @Autowired 注解也能自动注入
    public Boss(Car car) {
        this.car = car;
    }

    private String name;

    // 字段注入,被标注的字段 就会从 IoC 容器中取出赋值
//    @Autowired
    private Car car;

    // 方法注入,方法中的参数就会从 IoC 容器中获取
//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }
}

```
在自动依赖注入的时候,如果没有所依赖的 bean 就会报错.

但是如果自动依赖的值时可选的,即 下面这种类型的,那么 Spring 在依赖注入的时候,如果没有这样的 bean ,就不会赋值.

```java
    public Boss(Optional<Car> car) {
        if (car.isPresent()) {
            this.car = car.get();
        }
    }
```

