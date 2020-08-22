package com.itguang.pojo.bean;

import lombok.Data;

@Data
public class School {

    private Student student;


    public void setStudent(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }


}
