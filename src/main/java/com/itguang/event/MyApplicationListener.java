package com.itguang.event;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // ApplicationEvent 所有事件
        System.out.println("收到事件 ---> "+event);

    }
}
