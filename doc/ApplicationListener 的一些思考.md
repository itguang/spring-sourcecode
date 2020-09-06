

```java
@Configuration
@Import({MyApplicationListener.class})
public class ApplicationListenerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationListenerDemo.class);
        Student student = ctx.getBean(Student.class);
        ctx.publishEvent(new MyApplicationEvent(student));
        ctx.stop();
        ctx.close();
    }

    @Bean
    public Student student() {
        Student student = new Student();
        student.setUser(new User("小李子", 26, "17638166573"));
        student.setRank(100);
        return student;
    }

        
    // 注解监听，用在方法上，实现原理 看 EventListener 的 @see EventListenerMethodProcessor
    @EventListener(classes = {MyApplicationEvent.class})
    public void handleMyApplication(MyApplicationEvent event) {
        Student student = event.getStudent();
        System.out.println("@EventListener ---> " + student);

    }
}
```



Spring 中的事件机制使用了观察者模式，

1. IoC 容器启动时，扫描所有的 Listener 组价，每次发布一个 AppilcationEvent 就 循环这些 Listener 回调方法。




```
@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				// Invoke factory processors registered as beans in the context.
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				initMessageSource();

				// Initialize event multicaster for this context.
                // 初始化 多播器（ApplicationEventMulticaster)，把 beanFactory 对象传给他，让它扫描所有的 Listener
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				onRefresh();

				// Check for listener beans and register them.
                // 注册 Listener
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				finishRefresh();
			}
```

* 如何注册 Listener 的呢？

```java
/**
	 * Add beans that implement ApplicationListener as listeners.
	 * Doesn't affect other listeners, which can be added without being beans.
	 */
	protected void registerListeners() {
		// Register statically specified listeners first.
		for (ApplicationListener<?> listener : getApplicationListeners()) {
			getApplicationEventMulticaster().addApplicationListener(listener);
		}

		// Do not initialize FactoryBeans here: We need to leave all regular beans
		// uninitialized to let post-processors apply to them!
		String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false);
		for (String listenerBeanName : listenerBeanNames) {
			getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName);
		}

		// Publish early application events now that we finally have a multicaster...
		Set<ApplicationEvent> earlyEventsToProcess = this.earlyApplicationEvents;
		this.earlyApplicationEvents = null;
		if (!CollectionUtils.isEmpty(earlyEventsToProcess)) {
			for (ApplicationEvent earlyEvent : earlyEventsToProcess) {
				getApplicationEventMulticaster().multicastEvent(earlyEvent);
			}
		}
	}
```


* org.springframework.context.event.AbstractApplicationEventMulticaster.ListenerRetriever.getApplicationListeners

## 需要注意的是：

Listener 分为 两种来源：applicationListeners 和 applicationListenerBeans


```java
/**
	 * Helper class that encapsulates a specific set of target listeners,
	 * allowing for efficient retrieval of pre-filtered listeners.
	 * <p>An instance of this helper gets cached per event type and source type.
	 */
	private class ListenerRetriever {

		public final Set<ApplicationListener<?>> applicationListeners = new LinkedHashSet<>();

		public final Set<String> applicationListenerBeans = new LinkedHashSet<>();

		private final boolean preFiltered;

		public ListenerRetriever(boolean preFiltered) {
			this.preFiltered = preFiltered;
		}

		public Collection<ApplicationListener<?>> getApplicationListeners() {
			List<ApplicationListener<?>> allListeners = new ArrayList<>(
					this.applicationListeners.size() + this.applicationListenerBeans.size());
			allListeners.addAll(this.applicationListeners);
			if (!this.applicationListenerBeans.isEmpty()) {
				BeanFactory beanFactory = getBeanFactory();
				for (String listenerBeanName : this.applicationListenerBeans) {
					try {
						ApplicationListener<?> listener = beanFactory.getBean(listenerBeanName, ApplicationListener.class);
						if (this.preFiltered || !allListeners.contains(listener)) {
							allListeners.add(listener);
						}
					}
					catch (NoSuchBeanDefinitionException ex) {
						// Singleton listener instance (without backing bean definition) disappeared -
						// probably in the middle of the destruction phase
					}
				}
			}
			if (!this.preFiltered || !this.applicationListenerBeans.isEmpty()) {
				AnnotationAwareOrderComparator.sort(allListeners);
			}
			return allListeners;
		}
	}
```

![](https://itguang.oss-cn-beijing.aliyuncs.com/20200905220552.png)

