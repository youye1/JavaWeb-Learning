package test2;

import cn.youye.hibernate.test2.entity.Email;
import cn.youye.hibernate.test2.entity.Person;
import cn.youye.hibernate.test2.entity.Person2;
import cn.youye.hibernate.test2.entity2.Article;
import cn.youye.hibernate.test2.entity2.Type;
import cn.youye.hibernate.test2.entity4.Clazz;
import cn.youye.hibernate.test2.entity4.Student1;
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
 * 一对多关联映射、多对一关联映射测试类
 * Created by pc on 2016/9/8.
 */
public class Test1 {

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

    @After
    public void afterTest(){
        session.getTransaction().commit();
        session.close();
        factory.close();
    }

    /**
     * Person对Email
     * 单向一对多测试
     * 测试级联添加和删除
     */
    @Test
    public void test1() {
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
    }

    /**
     * 单向一对多测试
     * 测试List集合的延迟加载
     */
    @Test
    public void test2() {
        Person person = session.load(Person.class, "7346c97a-5585-47f9-80cd-30f07e14ba26");
        System.out.println("=====>person: " + person.getName());
    }

    /**
     * Person对String
     * 单向一对多测试
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
    }

    /**
     * Type对Article
     * 单向多对一测试
     * 测试级联数据添加删除
     */
    @Test
    public void test4(){
        Type type=new Type();
        type.setId(UUID.randomUUID().toString());
        type.setType("学术");

        Article article=new Article();
        article.setId(UUID.randomUUID().toString());
        article.setName("体感技术的发展学术论文");
        article.setContent("这是一个发展的世界，我们......");
        article.setType(type);

        session.persist(article);
        List<Article> list=session.createQuery("select a from Article a where a.name like '%论文%' ").list();
        for (Article article1:list){
            System.out.println("|----|"+article1.getName());
            System.out.println("|----|----"+article1.getType().getType());
            System.out.println("|----|----"+article1.getContent());
        }
//        session.delete(article);
    }

    /**
     * Clazz对Student1
     * 双向一对多多对一测试
     * 数据添加和删除
     */
    @Test
    public void test5(){
        Clazz clazz=new Clazz();
        clazz.setId(UUID.randomUUID().toString());
        clazz.setName("高三八班");

        Student1 stu1=new Student1();
        stu1.setId(UUID.randomUUID().toString());
        stu1.setName("潘隆成");
        stu1.setSex("男");

        Student1 stu2=new Student1();
        stu2.setId(UUID.randomUUID().toString());
        stu2.setName("胡林威");
        stu2.setSex("男");

        session.persist(clazz);
        session.persist(stu1);
        session.persist(stu2);
        stu1.setClazz(clazz);
        stu2.setClazz(clazz);

        session.save(stu1);
        session.save(stu2);
        session.getTransaction().commit();

        session.beginTransaction();
        Clazz c= (Clazz) session.createQuery("select c from Clazz c where c.name= :name").setParameter("name","高三七班").uniqueResult();

        session.refresh(c);//刷新，防止Hibernate缓存，从数据库从新读取数据

        System.out.println("|----|"+c.getName());
        for (Student1 stu:c.getStudent1s()){
            System.out.println("|----|----"+stu.getName()+"--"+stu.getSex());
        }

        List<Student1> student1s=session.createQuery("select s from Student1 s where s.clazz.name= :name").setParameter("name","高三七班").list();
        System.out.println("|----|高三七班的学生有");
        for (Student1 s:student1s){
            System.out.println("|----|----"+s.getName()+"--"+s.getSex());
        }

    }
}
