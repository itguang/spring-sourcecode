package com.itguang;

import com.itguang.pojo.User;
import com.itguang.pojo.bean.Student;
import com.itguang.pojo.bean.component.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 被 @Configuration 标志的类,相当于 spring-bean.xml 文件, 可以在此类中进行 Bean 的定义
 */
@Configuration
public class ConfigurationDemo {

    public static void main(String[] args) {
        /*
        Java-based Container Configuration 编程风格指的是：

            * 用 @Configuration 把一个普通 Java 类变成配置类，充当 XML
            * 在配置类中写多个方法，加上 @Bean 把返回值对象加到 Spring 容器中
            * 把配置类 AppConfig 喂给 AnnotationConfigApplicationContext，让它像解析 XML 一样解析配置类
            * 无需加 @Component 注解，因为我们可以手动 new 之后通过 @Bean 加入容器
         */

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConfigurationDemo.class);

        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        Student myStudent = applicationContext.getBean("myStudent", Student.class);

        System.out.println(myStudent);


    }


    @Bean
    public Person person() {
        Person person = new Person();
        person.setName("李四");
        person.setAge(18);
        return person;
    }

    @Bean
    public User getUser() {
        User user = new User();
        user.setName("itguang");
        user.setAge(25);
        user.setIdCard("1234567890");
        return user;
    }

    @Bean(name = "myStudent")
    public Student student(User user) {
        Student student = new Student();
        student.setUser(user);
        return student;

    }
}
