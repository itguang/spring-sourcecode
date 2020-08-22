package com.itguang.pojo.bean.component;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Data
public class Boss {

    // 构造函数注入,由于 Spring 在初始化 Boss 这个 Bean 时,会调用 这个带参数的构造器,
    // 那么Spring 就会 从 IoC 容器中找到 构造器中的参数 Car 把这个 car bean 实例注入进来
    // 所以,这里不用添加 @Autowired 注解也能自动注入
    // 但是,如果 没有 Car 这个 bean 就会导致创建Boss报错
//    public Boss(Car car) {
//        this.car = car;
//    }

    public Boss(Optional<Car> car) {
        if (car.isPresent()) {
            this.car = car.get();
        }
    }

    private String name;

    // 字段注入
//    @Autowired
    private Car car;

    // 方法注入
//    @Autowired
//    public void setCar(Car car) {
//        this.car = car;
//    }
}
