package com.itguang;

import com.itguang.pojo.bean.School;
import com.itguang.pojo.bean.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({School.class, Student.class})
public class ApplicationContextDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationContextDemo.class);
        School school = applicationContext.getBean(School.class);
        System.out.println(school.toString());
        applicationContext.registerShutdownHook();
    }



}
