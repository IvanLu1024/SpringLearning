import com.ivan.aop.MathCalculator;
import com.ivan.configure.MyConfigOfAOP;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_AOP {

    @Test
    public void test1(){

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfigOfAOP.class);
        MathCalculator calculator = applicationContext.getBean(MathCalculator.class);
        System.out.println(calculator.div(9,0));;

    }

    private void printBeans(ApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String b:beans){
            System.out.println(b);
        }

    }
}
