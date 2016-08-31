package cn.youye.spring.spel;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spel测试：spel操作范围
 * Created by pc on 2016/8/17.
 */
public class SpELCityTest {


    public static void main(String[] args) {
        testSpelList();
    }

    //测试类
    private static void testSpelList() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("config/config-spel.xml");

        SpelCityList spelCityList = (SpelCityList) context.getBean("spelCityList");


        System.out.println("ChoseCity1 : " + spelCityList.getChoseCity1().getName());
        System.out.println("ChoseCity2 : " + spelCityList.getChoseCity2().getName());
        System.out.println("aBigCity1 : " + spelCityList.getaBigCity1().getName());
        System.out.println("aBigCity2 : " + spelCityList.getaBigCity2().getName());
        System.out.println("BigCitys : " + spelCityList.getBigCitys().size());
        System.out.println("CityName1 : " + spelCityList.getCityName1());
        System.out.println("CityName2 : " + spelCityList.getCityName2());
        System.out.println("CityName3 : " + spelCityList.getCityName3());
    }
}
