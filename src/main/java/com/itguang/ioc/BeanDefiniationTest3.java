package com.itguang.ioc;

import com.itguang.pojo.bean.School;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDefiniationTest3 {

    public static void main(String[] args) {

        BeanFactory beanFactory  = new ClassPathXmlApplicationContext("classpath:spring.xml");

        School school = beanFactory.getBean("school", School.class);

        System.out.println(school);
    }

    private static BeanFactory bindWithXMLFile(DefaultListableBeanFactory registry) {

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("classpath:spring.xml");

        return registry;
    }
}
