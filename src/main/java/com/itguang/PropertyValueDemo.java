package com.itguang;

import com.itguang.pojo.bean.component.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * PropertySource 可以读取配置文件的属性保存到环境变量中
 */
@PropertySource(value = {"classpath:my.yml"})
@Configuration
public class PropertyValueDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(PropertyValueDemo.class);

        Person person = applicationContext.getBean(Person.class);

        System.out.println("---> person = "+person);

    }

    @Bean
    public Person person() {
        Person person = new Person();

        return person;
    }


}
