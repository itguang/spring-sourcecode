package com.itguang;

import com.itguang.factorybean.PersonFactoryBean;
import com.itguang.pojo.bean.component.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryBeanDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanDemo.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }

        // 第一次实例化 Person 对象
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);

        // 第二次实例化 Person 对象
        /*
            工厂 Bean ,
            FactoryBean 定义的 Bean 在注入的时候,需要注意类型是 FactoryBean 中的实际类型
         */

        // PersonFactoryBean personFactoryBean = applicationContext.getBean("getPersonFactoryBean",PersonFactoryBean.class);
        Person personFactoryBean = applicationContext.getBean("getPersonFactoryBean", Person.class);


        System.out.println(personFactoryBean);

    }


    // 再次注入的时候,实际会调用 com.itguang.factorybean.PersonFactoryBean.getObject() 方法注入 Person 对象
    @Bean
    public PersonFactoryBean getPersonFactoryBean() {
        PersonFactoryBean personFactoryBean = new PersonFactoryBean();
        return personFactoryBean;
    }
}
