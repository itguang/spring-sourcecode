package com.itguang.controller;

import com.itguang.service.SpiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SpiderController {

    @Autowired
    private SpiderService spiderService;

    public void run(){
        spiderService.setName("哈哈哈");
    }
}
