package com.itguang.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author lizengguang
 * @date 2021-12-27 14:24
 */
@Component
public class MyLazyUse {

    @Autowired
    @Lazy
    private MyLazy myLazy;

    public MyLazyUse() {
        System.out.println("MyLazyUse 构造方法执行。。。");
    }
}
