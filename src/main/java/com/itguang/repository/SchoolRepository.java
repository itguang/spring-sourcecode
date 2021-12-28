package com.itguang.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolRepository {

    @Autowired
    @Lazy
    private JdbcTemplate jdbcTemplate;

    private String schoolName = "家里蹲大学";


    public String getSchoolName() {
        return schoolName;
    }


    public void insert(String name, String address) {
        String sql = "INSERT INTO `spring-learn`.`school`( `name`, `address`) VALUES ( ?, ?);";
        jdbcTemplate.update(sql, name, address);
    }
}
