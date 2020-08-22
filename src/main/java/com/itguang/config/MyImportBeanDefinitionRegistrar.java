package com.itguang.config;

import com.itguang.anno.MyAnno;
import com.itguang.repository.StudentRepository;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.util.Set;

/**
 * 自定义注册Bean逻辑
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator) {

        String annotationName = MyAnno.class.getName();
        Set<MethodMetadata> annotatedMethods = importingClassMetadata.getAnnotatedMethods(annotationName);

        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();

        Set<String> metaAnnotationTypes = importingClassMetadata.getMetaAnnotationTypes(annotationName);

        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(StudentRepository.class);
        registry.registerBeanDefinition("mySchoolRepository", rootBeanDefinition);


    }
}
