package com.itguang.pojo.bean.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Person {

    public Person() {
        System.out.println("---> new Person() 构造方法...");
    }

    /**
     * 配置文件属性注入
     */
    @Value("${person.name:张三}")
    private String name = "张三";

    /**
     * EL 表达式
     */
    @Value("#{20-2}")
    private Integer age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
