package com.itguang.pojo.bean;

import com.itguang.pojo.Art;
import lombok.Data;

@Data
public class Human extends Animal implements Art {
    @Override
    public void paint() {

    }

    @Override
    public void sing() {

    }
}
