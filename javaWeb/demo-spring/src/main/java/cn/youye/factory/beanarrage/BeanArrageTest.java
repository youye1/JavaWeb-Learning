package cn.youye.factory.beanarrage;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pc on 2016/7/12.
 */
public class BeanArrageTest {

    @Test
    public void testBeanArrage() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("config/config-beanArrage.xml");

        Boss boss1 = beanFactory.getBean("boss1", Boss.class);
        Boss boss2 = beanFactory.getBean("boss2", Boss.class);
        Boss boss3 = beanFactory.getBean("boss2", Boss.class);

        System.out.println("boss1: " + boss1.toString());
        System.out.println("boss2: " + boss2.toString());
        System.out.println("boss3: " + boss3.toString());

    }
}
