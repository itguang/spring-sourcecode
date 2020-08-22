package com.itguang.pojo.bean.component;

import com.itguang.pojo.bean.School;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class MyAware implements ApplicationContextAware, EmbeddedValueResolverAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        School school = applicationContext.getBean(School.class);
        System.out.println("---> school = " + school);
        _printAllBeanName(applicationContext);

        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        EmbeddedValueResolver resolver = new EmbeddedValueResolver(configurableApplicationContext.getBeanFactory());

        _testResolver(resolver);

    }

    private static void _printAllBeanName(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }

    // SPEL 表达式解析器,可以解析环境变量中的值
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        _testResolver(resolver);
    }

    private void _testResolver(StringValueResolver resolver) {
        String value = resolver.resolveStringValue("${my.test:看不到你呢!}");
        System.out.println("---> ${my.test:看不到你呢!} = " + value);
        String defaultValue = resolver.resolveStringValue("${my.test1:看不到你呢!}");
        System.out.println("--->${my.test1:看不到你呢!} = " + defaultValue);
        // 解析EL表达式
        String elValue = resolver.resolveStringValue("#{20*18}");
        System.out.println("---> #{20*18} = " + elValue);

        String osName = resolver.resolveStringValue("${os.name}");
        System.out.println("---> ${os.name} = " + osName);
    }
}
