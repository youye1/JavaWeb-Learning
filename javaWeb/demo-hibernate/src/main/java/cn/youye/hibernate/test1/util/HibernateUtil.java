package cn.youye.hibernate.test1.util;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibrenate工具类，用于创建sessionFactory
 * Created by pc on 2016/9/8.
 */
public class HibernateUtil {
    //单例模式的SessionFactory
    private static SessionFactory sessionFactory;

    static {
        try {
            ServiceRegistry registry=new StandardServiceRegistryBuilder().configure().build();
            sessionFactory=new Configuration().buildSessionFactory(registry);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
