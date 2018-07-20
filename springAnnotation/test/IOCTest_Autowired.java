import com.ivan.configure.MyConfigOfAutowired;
import com.ivan.dao.BookDao;
import com.ivan.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_Autowired {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(MyConfigOfAutowired.class);
        //BookService bookService = applicationContext.getBean(BookService.class);



       printBeans(applicationContext);
        //System.out.println(bookService);

        /*BookDao bookDao = applicationContext.getBean(BookDao.class);
        System.out.println(bookDao);*/
        applicationContext.close();

    }

    private void printBeans(ApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String b:beans){
            System.out.println(b);
        }

    }
}
