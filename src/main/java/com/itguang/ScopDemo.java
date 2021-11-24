package com.itguang;

import com.itguang.controller.SpiderController;
import com.itguang.pojo.bean.Student;
import com.itguang.pojo.bean.component.Person;
import com.itguang.service.SpiderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SpiderController.class, SpiderService.class})
public class ScopDemo {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ScopDemo.class);
        System.out.println("---> Spring 容器加载完成! <---");
        Person person = applicationContext.getBean(Person.class);
        Student student = applicationContext.getBean(Student.class);

    }
}
