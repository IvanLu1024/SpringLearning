import com.ivan.configure.MyConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LifeCycle {

    @Test
    public void test01(){
        //1.创建IOC容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyConfigOfLifeCycle.class);
        System.out.println("容器创建");

        //2.关闭IOC容器
        ((AnnotationConfigApplicationContext) applicationContext).close();



    }
}
