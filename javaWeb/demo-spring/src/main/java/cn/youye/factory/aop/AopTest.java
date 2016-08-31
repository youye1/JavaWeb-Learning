package cn.youye.factory.aop;

import cn.youye.factory.bean.HelloDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pc on 2016/7/12.
 */
public class AopTest {
    @Test
    public void testHello() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/config-aoptest.xml");
        HelloDao helloDao = ctx.getBean("helloDaoImpl", HelloDao.class);
        helloDao.sayHello();
    }

}
