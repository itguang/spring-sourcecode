package com.itguang;

import org.springframework.context.ApplicationContext;

public class BaseDemo {

    public static void printAllBeanName(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }
}
