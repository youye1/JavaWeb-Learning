package cn.youye.spring.spel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spel测试：spel操作范围
 * Created by pc on 2016/8/17.
 */
public class SpELTest05 {


    public static void main(String[] args) {

        testSpelLiteral2();
    }

    private static void testSpelLiteral2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/config-spel.xml");
        SpelLiteral2 spelLiteral2 = ctx.getBean("spelLiteral2", SpelLiteral2.class);

        System.out.println(spelLiteral2.getPi());
        System.out.println(spelLiteral2.getRandom());

    }
}
