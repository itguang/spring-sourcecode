# 关于 Spring profile 的一些用法



```java
@Configuration
@PropertySource("classpath:my-profile.properties")
public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 设置环境变量
        // 获取 ApplicationContext 手动注册我们的 配置类
        // 刷新 IoC 容器
        applicationContext.getEnvironment().setActiveProfiles("test");
        applicationContext.register(ProfileDemo.class);
        applicationContext.refresh();
        _printAllBeanName(applicationContext);


    }

    /*
        激活环境变量,可以通过 `-Dspring.profile.active = test`
        也可以通过编程方式激活特定的 profile
        如果没有指定 profile, 那么指定了 profile(default除外) 的 bean 将不会注入进来
     */

    @Profile("default")
    @Bean("schoolDefault")
    public School schoolDefault() {
        School school = new School();
        school.setName("prod 学校");
        return school;

    }


    @Profile("prod")
    @Bean("schoolProd")
    public School schoolProd() {
        School school = new School();
        school.setName("prod 学校");
        return school;

    }

    @Profile("test")
    @Bean("schoolTest")
    public School schoolTest() {
        School school = new School();
        school.setName("test 学校");
        return school;

    }

    @Profile("dev")
    @Bean("schoolDev")
    public School schoolDev() {
        School school = new School();
        school.setName("dev 学校");
        return school;

    }

    private static void _printAllBeanName(ApplicationContext applicationContext) {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();

        for (String definitionName : definitionNames) {

            System.out.println("---> " + definitionName);
        }
    }
}


```