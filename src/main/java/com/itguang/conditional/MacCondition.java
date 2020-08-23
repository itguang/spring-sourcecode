package com.itguang.conditional;

import com.itguang.anno.MyAnno;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {

    /**
     * 可以在此方法中获取 IoC 上下文,和 当前 Bean 上的注解
     * @param context IoC 上下文
     * @param metadata 当前 Bean 上的注解
     * @return 是否需要注入当前 bean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Environment environment = context.getEnvironment();
        BeanDefinitionRegistry registry = context.getRegistry();

        MergedAnnotations annotations = metadata.getAnnotations();
        MergedAnnotation<MyAnno> myAnnoMergedAnnotation = annotations.get(MyAnno.class);

        String valueString = myAnnoMergedAnnotation.getString("value");


        String osName = environment.getProperty("os.name");
        if (osName.contains("Mac")) {
            return true;
        }
        return false;
    }
}
