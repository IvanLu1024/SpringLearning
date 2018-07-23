import com.ivan.aop.MathCalculator;
import com.ivan.configure.MyConfigOfAOP;
import com.ivan.ext.ExtConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 *
 *
 */
public class IOCTest_Ext {

    @Test
    public void test1(){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).close();



    }

    private void printBeans(ApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String b:beans){
            System.out.println(b);
        }

    }
}
