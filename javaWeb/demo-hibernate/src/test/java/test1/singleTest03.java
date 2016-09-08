package test1;

import cn.youye.hibernate.test1.entity.Student;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 单一主键的增删改查
 * Created by pc on 2016/9/1.
 */
public class singleTest03 {
    private Configuration cfg;
    private SessionFactory sfactory;
    private ServiceRegistry registry;
    private Session session;
    private Transaction transaction;

    @Before
    public void beforeTest() {
        cfg = new Configuration().configure();
        registry = new StandardServiceRegistryBuilder().configure().build();
        sfactory = cfg.buildSessionFactory(registry);
        session = sfactory.getCurrentSession();
        transaction = session.beginTransaction();
    }

    @After
    public void afterTest() {
        transaction.commit();//提交事务
//        session.close();//关闭会话
        sfactory.close();//关闭会化工厂
    }

    /**
     * 测试将Blob对象写入数据库中
     */
    @Test
    public void testWriteBlob(){
        Student student=new Student();
        File file=new File("E:\\JavaWorkSpace\\JavaWeb-Learning\\src\\"+"icon.jpg");

        try {
            InputStream input=new FileInputStream(file);
            Blob picture= Hibernate.getLobCreator(session).createBlob(input,input.available());
            student.setPicture(picture);
            student.setName("殷素素");
            student.setGender("女");
            session.save(student);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试从数据库读取Blob对象并写入文件中
     */
    @Test
    public void readBlob(){
        Student student=session.get(Student.class,2);
        Blob img=student.getPicture();
        try {
            //输入流
            InputStream input=img.getBinaryStream();
            File file=new File("E:\\JavaWorkSpace\\JavaWeb-Learning\\src\\"+"icon-read.jpg");
            //输出流
            OutputStream output=new FileOutputStream(file);
            //创建缓冲区
            byte[] buff=new byte[input.available()];
            input.read(buff);
            output.write(buff);
            input.close();
            output.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get(){
        /**
         * get和load在不考虑缓存的情况下，get方法会在调用之后立即向数据库发出SQL语句，返回持久化对象
         * 而load方法会在调用后返回一个代理对象，而这个代理对象只是保存了实体的主键id，
         * 只有在调用非主键属性时才会发出SQL语句
         */
        Student student=session.load(Student.class,1);
        Student student1=session.get(Student.class,1);
        System.out.println(student.toString());
        System.out.println(student1.toString());
    }

    @Test
    public void delete(){
        Student student=new Student();
        student.setId(1);
        session.delete(student);
    }

    @Test
    public void list(){
        List<Student> students=new ArrayList<>();
        String hsql="FROM Student";
        students=session.createQuery(hsql).list();
        System.out.println("====="+students.size());
        System.out.println("====="+students.toString());
    }
}
