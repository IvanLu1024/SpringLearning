import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class IOCTest_xml {

    @Test
    public void test1(){

        //读取xml文件获取资源
        ClassPathResource resource=new ClassPathResource("beans.xml");
        //创建DefaultListableBeanFactory
        /**
         * DefaultListableBeanFactory:
         * 典型的用法是先访问所有bean定义（可能从bean定义文件中读取），
         * 然后再访问bean。因此，bean定义查找在本地bean定义表中是一种廉价的操作，
         * 在预构建的bean定义元数据对象上运行。
         *
         */
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        //创建XmlBeanDefinitionReader实例
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        //读取器加载资源，这时候会将bean加入容器中
        int i = reader.loadBeanDefinitions(resource);
        BeanDefinitionRegistry beanFactory = reader.getBeanFactory();
        if (beanFactory==factory){
            System.out.println("相同");
        }else {
            System.out.println("不同");
        }
        String[] names = factory.getBeanDefinitionNames();
        for (String n:names){

            System.out.println(n);
        }

    }

    private void printBeans(ApplicationContext applicationContext){
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String b:beans){
            System.out.println(b);
        }

    }
}
