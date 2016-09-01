package test1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;


/**
 * 2.测试获取session对象，及两种方法的不同之处
 * Created by pc on 2016/9/1.
 */
public class test02 {
    private Configuration config;
    private ServiceRegistry registry;
    private SessionFactory factory;

    @Before
    public void beforeTest() {
        config = new Configuration();
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = config.buildSessionFactory(registry);
    }

    @Test
    public void testOpenSession() {

        Session session = factory.openSession();
        Session session1 = factory.openSession();
        if (session != null) {
            System.out.println("======session openSession创建成功");
        }
        if (session == session1) {
            System.out.println("=================true");
        } else {
            System.out.println("=================false");
        }
    }

    @Test
    public void testGetCurrentSession() {
        Session session = factory.getCurrentSession();
        Session session1 = factory.getCurrentSession();
        if (session != null) {
            System.out.println("======session getCurrentSession创建成功");
        }
        if (session == session1) {
            System.out.println("=================true");
        } else {
            System.out.println("=================false");
        }
    }
}
