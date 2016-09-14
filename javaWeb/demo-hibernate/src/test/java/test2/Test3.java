package test2;

import cn.youye.hibernate.test2.entity2.Article;
import cn.youye.hibernate.test2.entity2.Type;
import cn.youye.hibernate.test2.entity5.Husband;
import cn.youye.hibernate.test2.entity5.Wife;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * 一对一关系测试类
 * Created by pc on 2016/9/9.
 */
public class Test3 {
    private Configuration config;
    private ServiceRegistry registry;
    private SessionFactory factory;
    private Session session;

    @Before
    public void before() {
        config = new Configuration();
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = config.buildSessionFactory(registry);
        session = factory.openSession();
        session.beginTransaction();
    }

    @After
    public void after() {
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    /**
     * Wife对Husband
     * 测试单向一对一关联
     */
    @Test
    public void test1() {
        Wife wife = new Wife();
        wife.setId(UUID.randomUUID().toString());
        wife.setName("Sherry");

        Wife wife2 = new Wife();
        wife2.setId(UUID.randomUUID().toString());
        wife2.setName("Lily");

        Husband husband = new Husband();
        husband.setId(UUID.randomUUID().toString());
        husband.setName("Jack");

        wife.setHusband(husband);
//        wife2.setHusband(husband); //引用同一行数据会报错，唯一性约束
        session.persist(wife);
        session.persist(wife2);
        session.getTransaction().commit();

        session.beginTransaction();
        System.out.println("|----|查看所有夫妻关系，看你是不是单身狗");
        List<Wife> wifes = session.createQuery("select w from Wife w ").list();
        for (Wife w : wifes) {
            if (w.getHusband() == null) {
                System.out.println("|----|----" + w.getName() + " is a single dog ");
            } else {
                System.out.println("|----|----" + w.getName() + " LOVE " + w.getHusband().getName());
            }
        }

    }
}
