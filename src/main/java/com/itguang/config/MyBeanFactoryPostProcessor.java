package com.itguang.config;

import com.itguang.anno.MyAnno;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        printAllBeanName(beanFactory);

        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());
        MyAnno anno = beanFactory.findAnnotationOnBean("school", MyAnno.class);
        System.out.println("----> anno" + anno);

    }

    public static void printAllBeanName(ConfigurableListableBeanFactory applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }
}
