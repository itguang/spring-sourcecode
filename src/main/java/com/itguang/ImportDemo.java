package com.itguang;

import com.itguang.config.MyImportBeanDefinitionRegistrar;
import com.itguang.config.MyImportSelector;
import com.itguang.pojo.Red;
import com.itguang.pojo.Yellow;
import com.itguang.pojo.bean.component.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 使用 Import 导入多个 类作为 Spring IoC 容器的 Bean
 *
 * 需要注意, 使用 Import 默认是 懒加载 , 即 Lazy 模式,只有在 getBean() 时才会实例化 该 Bean
 */
@Configuration
@Import({Red.class, Yellow.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class ImportDemo {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ImportDemo.class);
        System.out.println("---> 容器加载完成");
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println("---> " + definitionName);
        }

        Person person = applicationContext.getBean(Person.class);


        System.out.println("---> getBean : person = "+person);

    }
}
