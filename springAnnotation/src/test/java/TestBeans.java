import com.ivan.configure.MainConfig;
import com.ivan.configure.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestBeans

{


    @Test
    public void test1(){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String n:names){
            System.out.println(n);
        }

        //TODO 默认是单实例的
        Object person = applicationContext.getBean("person");
        Object person1 = applicationContext.getBean("person");
        System.out.println("实例是否相等：");
        System.out.println(person==person1);


    }

}
