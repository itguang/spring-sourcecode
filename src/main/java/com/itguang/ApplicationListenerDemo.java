package com.itguang;

import com.itguang.event.MyApplicationEvent;
import com.itguang.event.MyApplicationListener;
import com.itguang.pojo.User;
import com.itguang.pojo.bean.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;


@Configuration
@Import({MyApplicationListener.class})
public class ApplicationListenerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationListenerDemo.class);
        Student student = ctx.getBean(Student.class);
        ctx.publishEvent(new MyApplicationEvent(student));
        ctx.stop();
        ctx.close();
    }

    @Bean
    public Student student() {
        Student student = new Student();
        student.setUser(new User("小李子", 26, "17638166573"));
        student.setRank(100);
        return student;
    }


    @EventListener(classes = {MyApplicationEvent.class})
    public void handleMyApplication(MyApplicationEvent event) {
        Student student = event.getStudent();
        System.out.println("@EventListener ---> " + student);

    }
}
