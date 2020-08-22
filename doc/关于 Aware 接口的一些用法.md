# 关于 Aware 接口的一些用法

实现不不不通的 Aware 接口可以获取不同的 Spring 内置 bean或者功能

```java
public class MyAware implements ApplicationContextAware, EmbeddedValueResolverAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        School school = applicationContext.getBean(School.class);
        System.out.println("---> school = " + school);
        _printAllBeanName(applicationContext);

        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
        EmbeddedValueResolver resolver = new EmbeddedValueResolver(configurableApplicationContext.getBeanFactory());

        _testResolver(resolver);

    }

    private static void _printAllBeanName(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }

    // SPEL 表达式解析器,可以解析环境变量中的值
    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        _testResolver(resolver);
    }

    private void _testResolver(StringValueResolver resolver) {
        String value = resolver.resolveStringValue("${my.test:看不到你呢!}");
        System.out.println("---> ${my.test:看不到你呢!} = " + value);
        String defaultValue = resolver.resolveStringValue("${my.test1:看不到你呢!}");
        System.out.println("--->${my.test1:看不到你呢!} = " + defaultValue);
        // 解析EL表达式
        String elValue = resolver.resolveStringValue("#{20*18}");
        System.out.println("---> #{20*18} = " + elValue);

        String osName = resolver.resolveStringValue("${os.name}");
        System.out.println("---> ${os.name} = " + osName);
    }
}

```


每一种 xxxAware 都对应这一个 xxxProcessor 进行处理
而一个 xxxProcessor 可以处理多个 xxxAware 接口
比如:

ApplicationContextAware,EmbeddedValueResolverAware,... -> ApplicationContextAwareProcessor
```java
class ApplicationContextAwareProcessor implements BeanPostProcessor {

	private final ConfigurableApplicationContext applicationContext;

	private final StringValueResolver embeddedValueResolver;


	/**
	 * Create a new ApplicationContextAwareProcessor for the given context.
	 */
	public ApplicationContextAwareProcessor(ConfigurableApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		this.embeddedValueResolver = new EmbeddedValueResolver(applicationContext.getBeanFactory());
	}


	@Override
	@Nullable
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (!(bean instanceof EnvironmentAware || bean instanceof EmbeddedValueResolverAware ||
				bean instanceof ResourceLoaderAware || bean instanceof ApplicationEventPublisherAware ||
				bean instanceof MessageSourceAware || bean instanceof ApplicationContextAware)){
			return bean;
		}

		AccessControlContext acc = null;

		if (System.getSecurityManager() != null) {
			acc = this.applicationContext.getBeanFactory().getAccessControlContext();
		}

		if (acc != null) {
			AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
				invokeAwareInterfaces(bean);
				return null;
			}, acc);
		}
		else {
			invokeAwareInterfaces(bean);
		}

		return bean;
	}

	private void invokeAwareInterfaces(Object bean) {
		if (bean instanceof EnvironmentAware) {
			((EnvironmentAware) bean).setEnvironment(this.applicationContext.getEnvironment());
		}
		if (bean instanceof EmbeddedValueResolverAware) {
			((EmbeddedValueResolverAware) bean).setEmbeddedValueResolver(this.embeddedValueResolver);
		}
		if (bean instanceof ResourceLoaderAware) {
			((ResourceLoaderAware) bean).setResourceLoader(this.applicationContext);
		}
		if (bean instanceof ApplicationEventPublisherAware) {
			((ApplicationEventPublisherAware) bean).setApplicationEventPublisher(this.applicationContext);
		}
		if (bean instanceof MessageSourceAware) {
			((MessageSourceAware) bean).setMessageSource(this.applicationContext);
		}
		if (bean instanceof ApplicationContextAware) {
			((ApplicationContextAware) bean).setApplicationContext(this.applicationContext);
		}
	}

}

```