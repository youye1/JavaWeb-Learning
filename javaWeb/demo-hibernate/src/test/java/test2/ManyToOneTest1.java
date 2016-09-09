package test2;

import cn.youye.hibernate.test2.entity2.Article;
import cn.youye.hibernate.test2.entity2.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * Created by pc on 2016/9/9.
 */
public class ManyToOneTest1 {
    private Configuration config;
    private ServiceRegistry registry;
    private SessionFactory factory;
    private Session session;

    @Before
    public void before(){
        config=new Configuration();
        registry=new StandardServiceRegistryBuilder().configure().build();
        factory=config.buildSessionFactory(registry);
        session=factory.openSession();
        session.beginTransaction();
    }

    @Test
    public void test1(){
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
        session.getTransaction().commit();
        session.close();
    }
}
