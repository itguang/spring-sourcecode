# Spring 容器创建过程



```java
@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			// 创建 BeanFactory（DefaultListableBeanFactory）
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			// 刷新容器，设置 SPEL 解析器，Property 解析器， 添加部分BeanPostProcess ，
			// 设置忽略自动装配的类型，设置可以自动装配的  bean , 添加变异时的AspectJ，注册 特性的组件如 Environment等等
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				// BeanFactory 准备工作完成后的 后置处理工作,子类可以重写这个方法，在 beanFactory 准备完成后做一些处理
				postProcessBeanFactory(beanFactory);

				// =================以上就是 BeanFactory 的创建以及预准备工作==========================

				// Invoke factory processors registered as beans in the context.
				// 在 BeanFactory 标准初始化后执行 BeanFactory 的后置处理器
				/*
				    BeanFactoryPostProcess 分为三类: 实现了 PriorityOrdered.class 和 Ordered.class 还有没有实现任何接口的
				*/
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				// 注册 BeanPostProcessor
				/*
						List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<>();
                		List<BeanPostProcessor> internalPostProcessors = new ArrayList<>();
                		List<String> orderedPostProcessorNames = new ArrayList<>();
                		List<String> nonOrderedPostProcessorNames = new ArrayList<>();
				    BeanPostProcessor 分为三类; 实现了 PriorityOrdered.class 和 Ordered.class ,没有实现任何接口的
				*/
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
			    /*
			        初始化 MessageSource 组件，（做国际化功能，消息绑定，消息解析）
			    */
				initMessageSource();

				// Initialize event multicaster for this context.
				/*
				    初始化事件派发器 EventMulticaster
				*/
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				/*
				    留给子类（子容器），重写这个方法，在容器刷新的时候可以自定义这个逻辑
				*/
				onRefresh();

				// Check for listener beans and register them.
				/*
				    把容器中的所有 Listener 注册到容器中，交给 事件派发器 EventMulticaster
				*/
				registerListeners();

				// Instantiate all remaining (non-lazy-init) singletons.
				/*
				    完成 beanFactory 的初始化，并且初始化所有剩余的单实例 bean
				*/
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
```


============ Spring Bean 的创建过程 ===========

后置处理器很重要，像一些增强注解，@Autowired ，@Async，@Aspect @Valid（参数校验） 都是靠 后置处理器完成的，

给我们的启发就是： 我们可以参考已有的后置处理器，在Spring基础上做开发，做扩展。










