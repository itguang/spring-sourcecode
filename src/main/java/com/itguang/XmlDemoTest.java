package com.itguang;

import com.itguang.pojo.bean.School;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlDemoTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        School school = applicationContext.getBean("school", School.class);

        System.out.println(school.toString());
    }
}
