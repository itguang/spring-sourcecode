package com.itguang.component;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author lizengguang
 * @date 2021-12-27 14:24
 */
@Component
@Lazy
public class MyLazy {

    public MyLazy() {
        System.out.println("MyLazy 构造方法执行。。。");
    }
}
