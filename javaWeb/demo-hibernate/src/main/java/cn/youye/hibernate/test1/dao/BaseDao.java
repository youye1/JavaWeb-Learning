package cn.youye.hibernate.test1.dao;

import cn.youye.hibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * DAO层
 * Created by pc on 2016/9/8.
 */
public class BaseDao<T> {

    /**
     * 插入数据
     *
     * @param object
     */
    public void create(T object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(object); //将数据保存到数据库
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 更新数据
     *
     * @param object
     */
    public void update(T object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 删除数据
     *
     * @param object
     */
    public void delete(T object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 根据ID 查找数据
     *
     * @param clazz
     * @param id
     */
    public T get(Class<T> clazz, Serializable id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            return (T) session.get(clazz, id);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    /**
     * 查找多条记录
     *
     * @param hsql
     */
    public List<T> findList(String hsql) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            return session.createQuery(hsql).list();
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

}
