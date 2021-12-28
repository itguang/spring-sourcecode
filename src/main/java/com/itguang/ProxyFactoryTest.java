package com.itguang;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryTest {

    public static void main(String[] args) {
        // 目标对象
        Object targetObject = null;
        // Aspect
        Advisor advisor = null;

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(targetObject);

        // 获取代理对象
        Object proxyObject = proxyFactory.getProxy();
    }
}
