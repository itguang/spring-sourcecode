package com.itguang;

import com.itguang.service.SchoolService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlComponentScanDemoTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-component-scan.xml");

        SchoolService school = applicationContext.getBean("schoolService", SchoolService.class);
//        SchoolService school = applicationContext.getBean(SchoolService.class);

        System.out.println(school.getSchoolName());
    }
}
