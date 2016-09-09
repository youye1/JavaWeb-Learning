package test2;

import cn.youye.hibernate.test2.entity3.Role;
import cn.youye.hibernate.test2.entity3.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * User和Role测试类
 * 多对多关联映射测试类
 * Created by pc on 2016/9/2.
 */
public class manyToManyTest1 {

    private Configuration config;
    private ServiceRegistry registry;
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    @Before
    public void before() {
        config = new Configuration();
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = config.buildSessionFactory(registry);
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void after() {
        transaction.commit();
        session.close();
        factory.close();
    }

    /**
     * 测试 单向多对多关联映射
     */
    @Test
    public void test1() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("游烨");
        user.setSex("女");
        User user1 = new User();
        user1.setId(UUID.randomUUID().toString());
        user1.setName("佳佳");
        user1.setSex("女");

        Role role1 = new Role();
        role1.setId(UUID.randomUUID().toString());
        role1.setName("经理");

        Role role2 = new Role();
        role2.setId(UUID.randomUUID().toString());
        role2.setName("主管");

        Role role3 = new Role();
        role3.setId(UUID.randomUUID().toString());
        role3.setName("总裁");

        user.getRoles().add(role1);
        user.getRoles().add(role2);
        user1.getRoles().add(role1);
        user1.getRoles().add(role3);

//        session.persist(user);
//        session.persist(user1);

        List<User> users = session.createQuery("select u from User u left join " +
                "fetch u.roles r where r.name = '经理'").list();
        for (User user2 : users) {
            System.out.println("|----|" + user2.getName());
            System.out.printf("|----|----");
            for (Role role : user2.getRoles()) {
                System.out.printf(role.getName() + " ");
            }
            System.out.println("");
        }
    }

}
