package test2;

import cn.youye.hibernate.test2.entity.Email;
import cn.youye.hibernate.test2.entity.Person;
import cn.youye.hibernate.test2.entity.Person2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * Person和Email测试类
 * 一对多关联映射
 * Created by pc on 2016/9/8.
 */
public class oneToManyTest1 {

    private Configuration config;
    private SessionFactory factory;
    private ServiceRegistry registry;
    private Session session;

    @Before
    public void beforeTest() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        config = new Configuration().configure();
        factory = config.buildSessionFactory(registry);
        session = factory.openSession();
        session.beginTransaction();
    }

    /**
     * 测试级联添加和删除
     */
    @Test
    public void test() {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName("佳佳");

        Email email = new Email();
        email.setId(UUID.randomUUID().toString());
        email.setEmail("jiajia@163.com");

        person.getEmails().add(email);
        email = new Email();
        email.setId(UUID.randomUUID().toString());
        email.setEmail("1354603271@qq.com");
        person.getEmails().add(email);

        session.persist(person);

        List list = session.createQuery("select p from Person p " +
                "left join fetch p.emails e where e.email like '%@qq.com%'").list();
        for (Person p : (List<Person>) list) {
            System.out.println("|----|" + p.getName());
            for (Email e : p.getEmails()) {
                System.out.println("|----|----" + e.getEmail());
            }
        }

//        session.delete(person);
        session.getTransaction().commit();

        session.close();
        factory.close();
    }

    /**
     * 测试List集合的延迟加载
     */
    @Test
    public void test2() {
        Person person = session.load(Person.class, "7346c97a-5585-47f9-80cd-30f07e14ba26");
        System.out.println("=====>person: " + person.getName());
    }

    /**
     * 测试String类型的实体数据的添加和删除
     */
    @Test
    public void test3(){
        Person2 person=new Person2();
        person.setName("阿芜");
        person.setId(UUID.randomUUID().toString());
        person.getEmails().add("19931011@163.com");
        person.getEmails().add("awuyoyo@gmail.com");

        session.persist(person);

        List list = session.createQuery("select p from Person2 p " +
                "left join fetch p.emails e where e like '%@163.com%'").list();
        for (Person2 p : (List<Person2>) list) {
            System.out.println("|----|" + p.getName());
            for (String e : p.getEmails()) {
                System.out.println("|----|----" + e);
            }
        }
        session.delete(person);
        session.getTransaction().commit();
        session.close();
    }
}
