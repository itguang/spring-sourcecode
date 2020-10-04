# 关于 Condition 的使用

`@Condition` 注解,只有满足条件的 Bean 才会注入到 IoC 容器中

```java
@FunctionalInterface
public interface Condition {

	/**
	 * Determine if the condition matches.
	 * @param context the condition context
	 * @param metadata the metadata of the {@link org.springframework.core.type.AnnotationMetadata class}
	 * or {@link org.springframework.core.type.MethodMetadata method} being checked
	 * @return {@code true} if the condition matches and the component can be registered,
	 * or {@code false} to veto the annotated component's registration
	 */
	boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);

}
```

```java
public class LinuxCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Environment environment = context.getEnvironment();
        BeanDefinitionRegistry registry = context.getRegistry();

        String osName = environment.getProperty("os.name");
        if (osName.contains("Linux")) {
            return true;
        }
        return false;
    }
}
```

自定义 Condition

```java
public class MacCondition implements Condition {

    /**
     * 可以在此方法中获取 IoC 上下文,和 当前 Bean 上的注解
     * @param context IoC 上下文
     * @param metadata 当前 Bean 上的注解
     * @return 是否需要注入当前 bean
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Environment environment = context.getEnvironment();
        BeanDefinitionRegistry registry = context.getRegistry();

        MergedAnnotations annotations = metadata.getAnnotations();
        MergedAnnotation<MyAnno> myAnnoMergedAnnotation = annotations.get(MyAnno.class);

        String valueString = myAnnoMergedAnnotation.getString("value");


        String osName = environment.getProperty("os.name");
        if (osName.contains("Mac")) {
            return true;
        }
        return false;
    }
}
```

使用 自定义 Condition
```java
    @Bean("jobs")
    @MyAnno("看得到我吗?")
    @Conditional({MacCondition.class})
    public Person bill() {
        Person person = new Person();
        person.setName("Jobs");
        person.setAge(60);
        return person;
    }
```