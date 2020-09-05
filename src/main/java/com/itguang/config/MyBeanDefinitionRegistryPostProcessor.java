package com.itguang.config;

import com.itguang.pojo.bean.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println("--->MyBeanDefinitionRegistryPostProcessor#definitionName = " + definitionName);
        }

        /*
            BeanDefinitionRegistry : Bean 定义信息保存中心，BeanFactory 就是按照 BeanDefinitionRegistry 保存的 BeanDefinition
            进行 Bean 创建操作的。也可以手动添加 BeanDefinition
         */

        // 手动注册一个 BeanDefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Student.class).getBeanDefinition();
        registry.registerBeanDefinition("my_custom_student",beanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
