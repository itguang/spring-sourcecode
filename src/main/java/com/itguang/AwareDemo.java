package com.itguang;

import com.itguang.pojo.bean.School;
import com.itguang.pojo.bean.component.MyAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyAware.class})
public class AwareDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AwareDemo.class);


    }


    @Bean
    public School school() {
        School school = new School();
        school.setName("家里蹲");
        return school;

    }
}
