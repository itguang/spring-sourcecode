package com.itguang.pojo.bean;

import com.itguang.pojo.User;
import lombok.Data;

@Data
public class Student {

    public Student() {
        System.out.println("---> new Student() 构造方法...");
    }

    private String number;

    private Integer rank;

    private Score score;

    private User user;

}
