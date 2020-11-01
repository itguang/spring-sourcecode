package beanprocessor;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * 初始化完成回调
 */
public class MyAfterSingletonInstantiatedProcessor implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("Singleton Bean initializing finished");
    }
}
