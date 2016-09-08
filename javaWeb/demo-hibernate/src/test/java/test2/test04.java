package test2;

import cn.youye.hibernate.test2.entity.Role;
import cn.youye.hibernate.test2.entity.User;
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

/**
 * 测试单向一对多关联
 * Created by pc on 2016/9/2.
 */
public class test04 {

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

    @Test
    public void testRole() {
        Role role = new Role();
        role.setName("技术经理");
        role.setDescription("负责技术开发及管理");
        User user =null;
        for (int i = 0; i < 5; i++) {
            user=new User();
            user.setUserName("userName"+i);
            user.setLoginName("loginName"+i);
            user.setPassword("123456"+"---"+i);
//            if (i%2==0){
//                user.setRoleId("2");
//            }else {
//                user.setRoleId("1");
//            }
            role.getUsers().add(user);
        }
        session.persist(role);

        List<User> users=session.createQuery("select r from role r left join fetch " +
                "r.users u where u.user_name like 'user%'").list();
        for (User user1:users){
            System.out.println("====="+user1.getUserName()+"--"+user1.getLoginName());
        }
    }

}
