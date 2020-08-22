package com.itguang;

import com.itguang.pojo.bean.component.Boss;
import com.itguang.pojo.bean.component.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Autowried 注解 可以用在 构造器,属性,方法上
 */
@Configuration
//@ComponentScan({"com.itguang.pojo"})
// 使用 Import 注入特定的 bean
//@Import({Boss.class, Car.class})
@Import({ Boss.class})
public class AutowriedDemo {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowriedDemo.class);
        _printAllBeanName(applicationContext);

        // 测试 @Autowried 可以用在 构造函数,属性,方法上

        Boss boss = applicationContext.getBean(Boss.class);

        System.out.println(boss);


    }

    private static void _printAllBeanName(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }


}
