package com.itguang;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(value = "com.itguang", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Configuration.class, Bean.class, RestController.class})
})
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ComponentScanDemo.class);
        applicationContext.refresh();

        _printAllBeanName(applicationContext);


        applicationContext.close();
    }

    private static void _printAllBeanName(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }
}
