package com.itguang.pojo;

import com.itguang.anno.MyAnno;

@MyAnno("哈哈哈,泥看得到我?")
public class Blue {

    @MyAnno("我在方法上哦")
    public String say(){
        return "蓝色啊";
    }
}
