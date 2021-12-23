package com.itguang.ioc;

import com.itguang.pojo.bean.School;
import com.itguang.pojo.bean.Student;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class BeanDefiniationTest {

    public static void main(String[] args) {
        DefaultListableBeanFactory registry = new DefaultListableBeanFactory();
       BeanFactory beanFactory =  bind(registry);

        School school = beanFactory.getBean("school", School.class);

        System.out.println(school);
        ((ConfigurableListableBeanFactory)beanFactory).destroySingletons();
    }

    private static BeanFactory bind(DefaultListableBeanFactory registry) {

        RootBeanDefinition schoolDefinition = new RootBeanDefinition(School.class);
        schoolDefinition.setLazyInit(true);
        RootBeanDefinition studentDefinition = new RootBeanDefinition(Student.class);

        // 给属性 name 赋值
        MutablePropertyValues schoolPropertyValues = new MutablePropertyValues();
        schoolPropertyValues.addPropertyValue("name","测试");
        schoolPropertyValues.addPropertyValue(new PropertyValue("student",studentDefinition));

        schoolDefinition.setPropertyValues(schoolPropertyValues);
        registry.registerBeanDefinition("school",schoolDefinition);

        return registry;
    }
}
