package com.itguang;

import com.itguang.pojo.bean.component.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigDemo {
    public static void main(String[] args) {
        /*

        注解 @Configuration，目的是让我们可以把一个普通的 Java 类等同于一个 XML 文件，
        而这个 Java 类就是 JavaConfig，我们习惯称之为配置类。
         */

//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        // AnnotationConfigApplicationContext是Spring用来专门针对注解开发的ApplicationContext子类
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);


        Person person = applicationContext.getBean(Person.class);

        System.out.println(person.toString());


    }
}
