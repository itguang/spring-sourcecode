package com.itguang.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolRepository {

    private String schoolName = "家里蹲大学";


    public String getSchoolName(){
        return schoolName;
    }
}
