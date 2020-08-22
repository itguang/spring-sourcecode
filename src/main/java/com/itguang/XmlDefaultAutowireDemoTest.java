package com.itguang;

import com.itguang.pojo.bean.School;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlDefaultAutowireDemoTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-default-autowire.xml");

        School school = applicationContext.getBean("school", School.class);

        System.out.println(school.toString());
    }
}
