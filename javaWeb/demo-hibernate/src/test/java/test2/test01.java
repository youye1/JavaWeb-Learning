package test2;

import cn.youye.hibernate.test2.entity.Email;
import cn.youye.hibernate.test2.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by pc on 2016/9/8.
 */
public class test01 {

    private Configuration config;
    private SessionFactory factory;
    private ServiceRegistry registry;
    private Session session;
    private Transaction transaction;

    @Before
    public void beforeTest() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        config = new Configuration().configure();
        factory = config.buildSessionFactory(registry);
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void test() {
        Person person = new Person();
        person.setName("游烨");

        Email email = new Email();
        email.setEmail("youye@123.com");

        person.getEmails().add(email);
        email = new Email();
        email.setEmail("2368380949@qq.com");
        person.getEmails().add(email);

        session.persist(person);

        List list = session.createQuery("select p from Person p " +
                "left join fetch p.emails e where e.email like '%@qq.com%'").list();
        for (Person p : (List<Person>)list) {
            System.out.println("|----|"+p.getName());
            for (Email e :p.getEmails()) {
                System.out.println("|----|----|"+e.getEmail());
            }
        }
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

}
