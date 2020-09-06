package com.itguang.event;

import com.itguang.pojo.bean.Student;
import org.springframework.context.ApplicationEvent;

public class MyApplicationEvent extends ApplicationEvent {

    public MyApplicationEvent(Student student) {
        super(student);
    }

    public Student getStudent() {
        return (Student) getSource();
    }

}
