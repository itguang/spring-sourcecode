package com.itguang;

import com.itguang.beanpostprocessor.CglibAopPostProcessor;
import com.itguang.pojo.bean.Student;
import com.itguang.service.StudentService;
import com.itguang.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 模拟 Spring AOP 的底层实现
 */
@Configuration
@Import({CglibAopPostProcessor.class, UserService.class, StudentService.class})
public class SpringAOPSimulation {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAOPSimulation.class);
        StudentService studentService = ctx.getBean(StudentService.class);
        studentService.save(new Student());

        UserService userService = ctx.getBean(UserService.class);
        userService.login("user", "password");

    }
}
