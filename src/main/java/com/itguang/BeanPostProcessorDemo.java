package com.itguang;

import com.itguang.config.MyBeanPostProcessor;
import com.itguang.pojo.bean.Student;
import com.itguang.pojo.bean.component.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@Import(MyBeanPostProcessor.class)
public class BeanPostProcessorDemo {
    public static void main(String[] args) {

        /*
            BeanPostProcessor: Bean 后置处理器,在 Bean 初始化前后执行.
            需要注意的是: 区分延迟初始化的 Bean ,只有在获取该 bean 时才会执行后置处理器
         */

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorDemo.class);
        Student student = applicationContext.getBean(Student.class);
        Student student2 = applicationContext.getBean("student", Student.class);
    }

    @Bean
    public Person getPerson() {
        System.out.println("---> getPerson()");
        Person person = new Person();
        return person;
    }

    @Bean
    @Lazy
    public Student getStudent() {
        System.out.println("---> getStudent()");
        Student student = new Student();
        return student;
    }
}
