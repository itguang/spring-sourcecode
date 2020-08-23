package com.itguang;

import com.itguang.anno.MyAnno;
import com.itguang.conditional.LinuxCondition;
import com.itguang.conditional.MacCondition;
import com.itguang.pojo.bean.component.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConditionalDemo {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalDemo.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("---> beanDefinitionName = " + beanDefinitionName);
        }
        Environment environment = applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");

        System.out.println("---> osName = " + osName);


        Person person = applicationContext.getBean(Person.class);
        System.out.println("---> person = " + person);


    }

    @Bean("jobs")
    @MyAnno("看得到我吗?")
    @Conditional({MacCondition.class})
    public Person bill() {
        Person person = new Person();
        person.setName("Jobs");
        person.setAge(60);
        return person;
    }

    @Bean("linus")
    @Conditional({LinuxCondition.class})
    public Person linus() {
        Person person = new Person();
        person.setName("linus");
        person.setAge(48);
        return person;
    }


}
