package com.itguang;

import com.itguang.pojo.bean.Student;
import com.itguang.pojo.bean.component.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class LazyDemo {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(LazyDemo.class);
        System.out.println("---> Spring 容器加载完成! <---");
        Person person = applicationContext.getBean(Person.class);
        Student student = applicationContext.getBean(Student.class);

        /*
        ---> getPerson()
        ---> new Person() 构造方法...
        ---> Spring 容器加载完成! <---
        ---> getStudent()
        ---> new Student() 构造方法...
         */

        Person person2 = applicationContext.getBean(Person.class);
        Student student2 = applicationContext.getBean(Student.class);

    }

    @Bean
    public Person getPerson() {
        System.out.println("---> getPerson()");
        Person person = new Person();
        return person;
    }

    @Bean
    @Lazy
    public Student getStudent() {
        System.out.println("---> getStudent()");
        Student student = new Student();
        return student;
    }
}
