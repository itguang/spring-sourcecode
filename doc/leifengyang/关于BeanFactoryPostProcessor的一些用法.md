

## BeanPostProcessor 和 BeanFactoryPostProcessor 和 BeanDefinitionRegistryPostProcessor

* BeanPostProcessor：

时机： bean 初始化前后

* BeanFactoryPostProcessor： 

时机： BeanFactory 初始化后，所有 bean 定义都已保存在 BeanFactory 中，但还未实例化

* BeanDefinitionRegistryPostProcessor

时机： 所有的 BeanDefinition 被加载之后，所有的 Bean 还未实例化


> BeanDefinitionRegistryPostProcessor 优先于 BeanFactoryPostProcessor 执行

```java

@Import(MyBeanFactoryPostProcessor.class)
@Configuration
public class BeanFactoryPostProcessorDemo {
    
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(BeanFactoryPostProcessorDemo.class);
    }
    
    @Bean
    @MyAnno(value = "看看看")
    public School school() {
        return new School();
    }
}

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        printAllBeanName(beanFactory);

        beanFactory.addBeanPostProcessor(new MyBeanPostProcessor());
        MyAnno anno = beanFactory.findAnnotationOnBean("school", MyAnno.class);
        System.out.println("----> anno" + anno);

    }

    public static void printAllBeanName(ConfigurableListableBeanFactory applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }
}
```


```java
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println("--->MyBeanDefinitionRegistryPostProcessor#definitionName = " + definitionName);
        }

        /*
            BeanDefinitionRegistry : Bean 定义信息保存中心，BeanFactory 就是按照 BeanDefinitionRegistry 保存的 BeanDefinition
            进行 Bean 创建操作的。也可以手动添加 BeanDefinition
         */

        // 手动注册一个 BeanDefinition
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Student.class).getBeanDefinition();
        registry.registerBeanDefinition("my_custom_student",beanDefinition);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
```

源码流程：
参考： `org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(org.springframework.beans.factory.config.ConfigurableListableBeanFactory, java.util.List<org.springframework.beans.factory.config.BeanFactoryPostProcessor>)`


总结起来就是：通过下面的方式获取所有 BeanFactoryPostProcessor 类型的 Bean
```java
String[] postProcessorNames =
					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);

beanFactory.getBean(ppName, BeanFactoryPostProcessor.class)
```

并且分为有顺序和无顺序的两种，再循环调用

```java
	/**
	 * Invoke the given BeanFactoryPostProcessor beans.
	 */
	private static void invokeBeanFactoryPostProcessors(
			Collection<? extends BeanFactoryPostProcessor> postProcessors, ConfigurableListableBeanFactory beanFactory) {

		for (BeanFactoryPostProcessor postProcessor : postProcessors) {
			postProcessor.postProcessBeanFactory(beanFactory);
		}
	}
```

