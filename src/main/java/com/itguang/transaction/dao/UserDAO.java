package com.itguang.transaction.dao;

import com.itguang.pojo.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class UserDAO {

    @Transactional(rollbackFor = RuntimeException.class)
    public void insert(User User) {

        System.out.println("保存 user ");

    }
}
