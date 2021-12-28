package com.itguang;

import com.itguang.aspect.LogAdvices;
import com.itguang.service.SchoolService;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

/**
 * @author lizengguang
 * @date 2021-12-22 18:51
 */
public class LogAdvicesTest {
    public static void main(String[] args) {
        AspectJProxyFactory weaver = new AspectJProxyFactory();
        weaver.setProxyTargetClass(true);
        weaver.setTarget(new SchoolService());
        weaver.addAspect(LogAdvices.class);
        SchoolService schoolService = weaver.getProxy();

        schoolService.doSomething("tuhu");

    }
}
