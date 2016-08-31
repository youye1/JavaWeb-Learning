package cn.youye.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pc on 2016/8/18.
 */
public class SpringTest {
    public static void main(String[] args) {
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("config/config-aoptest.xml");
        Student student= (Student) ctx.getBean("stu");
        student.show("youye");
//        student.show("jiajia");
    }
}
