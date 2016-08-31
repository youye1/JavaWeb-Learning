package cn.youye.aop.xml;

/**
 * 1.创建实体类
 * Created by pc on 2016/8/18.
 */
public class Student {

    public String show(String name){
        System.out.println(name);
        return "hello Spring AOP";
    }
}
