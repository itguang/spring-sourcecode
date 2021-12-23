package com.itguang.beanpostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib 模拟实现 Spring 动态代理
 */
public class CglibAopPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {


        // enhancer: 增强者
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(bean.getClass().getClassLoader());
        enhancer.setSuperclass(bean.getClass());
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] arguments, MethodProxy methodProxy) throws Throwable {
                System.out.println("Cglib 代理方法前 。。。");
                // 调用代理的对象的方法
                Object ret = method.invoke(bean, arguments);
                System.out.println("Cglib 代理方法后 。。。");
                return ret;
            }
        };
        enhancer.setCallback(methodInterceptor);
        // 创建代理 Bean
        Object proxyBean = enhancer.create();

        // 返回代理对象
        return proxyBean;
    }
}
