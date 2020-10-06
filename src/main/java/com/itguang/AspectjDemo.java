package com.itguang;

import com.itguang.aspect.LogAdvices;
import com.itguang.service.SchoolService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SchoolService.class, LogAdvices.class})
@EnableAspectJAutoProxy // …启动 Spring AOP 动态代理
public class AspectjDemo extends BaseDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AspectjDemo.class);
        printAllBeanName(applicationContext);


        // 使用 LogAdvices 切面 对 SchoolService#getSchoolName() 方法进行增强

        SchoolService schoolService = applicationContext.getBean(SchoolService.class);
        String schoolName = schoolService.getSchoolName();

        System.out.println("---> schoolName = " + schoolName);

    }
}
