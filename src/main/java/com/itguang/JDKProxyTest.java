package com.itguang;

import com.itguang.service.IUserService;
import com.itguang.service.UserService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxyTest {

    public static void main(String[] args) {
        testJdkProxy();
//        testCglibProxy();
    }

    public static void testJdkProxy() {
        IUserService userService = new UserService();

        IUserService userServiceProxy = (IUserService) Proxy.newProxyInstance(JDKProxyTest.class.getClassLoader(),
                userService.getClass().getInterfaces(),
                (proxy, method, arguments) -> {
                    System.out.println("我是代理方法前中运行的 。。。");

                    // 原始方法运行
                    Object obj = method.invoke(userService, arguments);

                    System.out.println("我是代理方法前后运行的 。。。");

                    return obj;
                });

        userServiceProxy.login("123456", "0000");
    }

    public static void testCglibProxy() {
        UserService userService = new UserService();
        // enhancer: 增强者
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(UserService.class.getClassLoader());
        enhancer.setSuperclass(UserService.class);
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] arguments, MethodProxy methodProxy) throws Throwable {

                System.out.println("Cglib 代理方法前 。。。");
                // 调用代理的对象的方法
                Object ret = method.invoke(userService, arguments);
                System.out.println("Cglib 代理方法后 。。。");
                return ret;
            }
        };
        enhancer.setCallback(methodInterceptor);
        UserService cglibProxyUserService = (UserService) enhancer.create();

        cglibProxyUserService.login("12234", "0000");

    }
}
