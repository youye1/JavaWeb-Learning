package cn.youye.spring.spelCheck;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by pc on 2016/8/17.
 */
public class TestProvince {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("config/config-spel.xml");
        ResultObj resultObj= (ResultObj) ctx.getBean("resultObj");

        System.out.println(resultObj.getProvinceList1());
        System.out.println(resultObj.getProvinceList2());
    }
}
