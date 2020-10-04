package com.itguang;

import com.itguang.beanpostprocessor.CglibAopPostProcessor;
import com.itguang.pojo.bean.Student;
import com.itguang.service.StudentService;
import com.itguang.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * 模拟 Spring AOP 的底层实现
 */
//@Configuration  @Configuration 与 @Import 不能一起使用
@Import({CglibAopPostProcessor.class, UserService.class})
public class SpringAOPSimulation {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringAOPSimulation.class);
        StudentService studentService = ctx.getBean(StudentService.class);
        studentService.save(new Student());

        UserService userService = ctx.getBean(UserService.class);
        userService.login("user", "password");

    }

    @Bean
    public StudentService studentService() {
        StudentService studentService = new StudentService();
        return studentService;
    }
}
