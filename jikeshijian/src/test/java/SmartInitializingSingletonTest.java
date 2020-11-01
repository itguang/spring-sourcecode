import bean.Book;
import beanprocessor.MyAfterSingletonInstantiatedProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import service.BookService;

@Configuration
@Import({BookService.class, MyAfterSingletonInstantiatedProcessor.class, Book.class})
public class SmartInitializingSingletonTest {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SmartInitializingSingletonTest.class);

    }


}
