import com.ivan.configure.MyConfigOfPropertyValues;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_PropertyValue {
    AnnotationConfigApplicationContext applicationContext;

    @Test
    public void test01(){
        applicationContext=new AnnotationConfigApplicationContext(MyConfigOfPropertyValues.class);


        Object person = applicationContext.getBean("person");
        System.out.println(person);
        System.out.println();
        printBeans(applicationContext);
        applicationContext.close();

    }


    private void printBeans(ApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String b:beans){
            System.out.println(b);
        }

    }
}
