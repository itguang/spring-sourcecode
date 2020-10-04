package com.itguang;

import com.itguang.component.MyComponent;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author guang
 * @since 2020/9/18 10:30 上午
 */
@Configurable
@Import({MyComponent.class})
public class ConfigValueDemo {

    public static void main(String[] args) {
       // #{''.split(',')}
        String[] split = "".split(",");


        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigValueDemo.class);
        MyComponent myComponent = ctx.getBean(MyComponent.class);
        System.out.println("myComponent.getIds() = " + myComponent.getIds());
    }


    public void test(){

    }

}
