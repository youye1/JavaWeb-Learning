package cn.youye.spring.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spel测试：基于注解
 * Created by pc on 2016/8/17.
 */
public class SpELTest03 {

    @Value("#{'hello '+ world}")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("config/config-spel.xml");
        SpELTest03 helloBean1 = ctx.getBean("helloBean1", SpELTest03.class);
        SpELTest03 helloBean2 = ctx.getBean("helloBean2", SpELTest03.class);

        System.out.println("helloBean1: " + helloBean1.getValue());
        System.out.println("helloBean2: " + helloBean2.getValue());
    }
}
