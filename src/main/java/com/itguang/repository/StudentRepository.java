package com.itguang.repository;

import com.itguang.pojo.bean.Student;
import org.springframework.stereotype.Component;

@Component("studentRepository")
public class StudentRepository {

    public void save(Student Student) {
        System.out.println("---> StudentRepository.save() ...");
    }
}
