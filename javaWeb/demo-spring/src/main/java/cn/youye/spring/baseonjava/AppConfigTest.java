package cn.youye.spring.baseonjava;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by pc on 2016/8/16.
 */
public class AppConfigTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(ApplicationContextConfig.class);

        System.out.println(ctx.getBean("message"));
    }
}
