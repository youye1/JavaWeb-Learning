package cn.youye.spring.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spel测试：spel操作范围
 * Created by pc on 2016/8/17.
 */
public class SpELTest04 {


    public static void main(String[] args) {

        testSpelLiteral();
    }

    private static void testSpelLiteral(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/config-spel.xml");
        SpelLiteral spelLiteral=ctx.getBean("spelLiteral",SpelLiteral.class);

        System.out.println("count: "+spelLiteral.getCount());
        System.out.println("message: "+spelLiteral.getMessage());
        System.out.println("frequency: "+spelLiteral.getFrequency());
        System.out.println("capacity: "+spelLiteral.getCapacity());
        System.out.println("name1: "+spelLiteral.getName1());
        System.out.println("name2: "+spelLiteral.getName2());
        System.out.println("enabled: "+spelLiteral.isEnabled());
    }
}
