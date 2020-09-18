package com.itguang.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class MyComponent {

    @Value("#{'${zf.ids:}'.split(',')}")
    private List<Integer> ids;

    public MyComponent() {
        System.out.println("---> MyConponent 构造方法。。。");
    }

}
