package com.itguang;

import com.itguang.anno.MyAnno;
import com.itguang.config.MyBeanDefinitionRegistryPostProcessor;
import com.itguang.config.MyBeanFactoryPostProcessor;
import com.itguang.pojo.bean.School;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({MyBeanFactoryPostProcessor.class, MyBeanDefinitionRegistryPostProcessor.class})
@Configuration
public class BeanDefinitionRegistryPostProcessorDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanDefinitionRegistryPostProcessorDemo.class);
    }

    @Bean
    @MyAnno(value = "看看看")
    public School school() {
        return new School();
    }
}
