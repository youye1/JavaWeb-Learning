package test1;

import cn.youye.hibernate.test1.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by pc on 2016/9/1.
 */
public class test01 {
    private Configuration cfg;
    private SessionFactory sfactory;
    private ServiceRegistry registry;
    private Session session;
    private Transaction transaction;

    @Before
    public void beforeTest() {
        //默认的配置文件可不写configure的参数。
        cfg = new Configuration().configure();
        //创建服务注册对象
        registry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂
        sfactory = cfg.buildSessionFactory(registry);
        //会话对象
        session = sfactory.openSession();
        //开启事务
        transaction = session.beginTransaction();
    }

    @After
    public void afterTest() {
        transaction.commit();//提交事务
        session.close();//关闭会话
        sfactory.close();//关闭会化工厂
    }

    @Test
    public void createStudent() {
        MetadataImplementor implementor = (MetadataImplementor) new MetadataSources(registry).buildMetadata();
        new SchemaExport(implementor).create(true, true);
    }

    @Test
    public void save() {

        Student student = new Student();
        student.setName("张翠山");
        student.setGender("男");
        student.setAddress("武当山");
        student.setBirthday(new Date());
        //可以设置事务自动提交模式
//        session.doWork(new Work() {
//            @Override
//            public void execute(Connection connection) throws SQLException {
//                connection.setAutoCommit(true);
//            }
//        });
        //保存数据
        session.save(student);
        //设置为自动提交模式，则强制发出sql语句，写到数据
//        session.flush();
    }
}
