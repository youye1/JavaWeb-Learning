package cn.youye.factory.bean;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pc on 2016/7/12.
 */
public class BeanTest {

    /**
     * 无参构造器
     */
    @Test
    public void testHelloWithoutArgs() {
        ApplicationContext context = new ClassPathXmlApplicationContext("config/config-all-test.xml");

        HelloDao helloDao = context.getBean("beanWithoutArgs", HelloDao.class);
        helloDao.sayHello();

    }

    /**
     * 有参构造器
     */
    @Test
    public void testHelloWithArgs() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("config/config-all-test.xml");

        HelloDao helloDao = beanFactory.getBean("beanWithArgs", HelloDao.class);
        helloDao.sayHello();

    }

    /**
     * 静态工厂
     */
    @Test
    public void testHelloStaticFactory() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("config/config-all-test.xml");

        HelloDao helloDao = beanFactory.getBean("beanByStaticFactory", HelloDao.class);
        helloDao.sayHello();

    }

    /**
     * 实例工厂
     */
    @Test
    public void testHelloInstanceFactory() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("config/config-all-test.xml");

        HelloDao helloDao = beanFactory.getBean("beanInstance", HelloDao.class);
        helloDao.sayHello();

    }
}
