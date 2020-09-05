package com.itguang;

import com.itguang.config.TxConfig;
import com.itguang.repository.SchoolRepository;
import com.itguang.service.SchoolService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@Import({TxConfig.class, SchoolService.class, SchoolRepository.class})
public class TransactionDemo extends BaseDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TransactionDemo.class);
        printAllBeanName(ctx);
        SchoolService schoolService = ctx.getBean(SchoolService.class);
        schoolService.insert("北京大学", "北京");

    }
}
