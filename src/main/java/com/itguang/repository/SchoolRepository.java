package com.itguang.repository;

import org.springframework.stereotype.Component;

@Component
public class SchoolRepository {

    private String schoolName = "家里蹲大学";


    public String getSchoolName(){
        return schoolName;
    }
}
