package cn.youye.spring.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spel测试：基于xml的配置
 * Created by pc on 2016/8/17.
 */
public class SpELTest02 {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/config-spel.xml");
        String hello1 = ctx.getBean("hello1", String.class);
        String hello2 = ctx.getBean("hello2", String.class);
        String hello3 = ctx.getBean("hello3", String.class);
        System.out.println("hello1: " + hello1);
        System.out.println("hello2: " + hello2);
        System.out.println("hello3: " + hello3);
    }
}
