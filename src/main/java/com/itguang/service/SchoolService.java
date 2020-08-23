package com.itguang.service;

import com.itguang.repository.SchoolRepository;
import com.itguang.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SchoolService {

    // @Autowired 默认采用 byType 模式自动装配，如果找到多个同类型的，会根据名字匹配。都不匹配，则会报错。
    // 如果遇见多个类型的 Bean ,又不能通过名称自动指定,那么可以使用 @Qualifier() 指定 bean name .
    @Autowired
    private SchoolRepository schoolRepository;


    // @Resource 和 Autowired 一样,唯一的区别就是,@Resource 可以自己指定 bean name
    @Resource(name = "studentRepository")
    private StudentRepository studentRepository;

    public String getSchoolName() {
        return schoolRepository.getSchoolName();
    }


}
