import com.ivan.beans.Person;
import com.ivan.configure.MainConfig;
import com.ivan.configure.MainConfig2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

public class TestDemo {


    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);


    @Test
    public void test01(){
        //动态获取当期系统的操作系统的信息
        Environment environment = applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println("此时的OS为："+osName);

        String[] names = applicationContext.getBeanNamesForType(Person.class);
        for (String n:names){
            System.out.println(n);
        }
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        System.out.println(personMap);


    }

    @Test
    public void testImport(){
        printBeans(applicationContext);


    }

    private void printBeans(ApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String b:beans){
            System.out.println(b);
        }

    }
}
