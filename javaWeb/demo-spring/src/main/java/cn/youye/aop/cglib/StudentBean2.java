package cn.youye.aop.cglib;

/**
 * 接口实现类
 * Created by pc on 2016/8/18.
 */
public class StudentBean2{
    private String name;

    public StudentBean2() {
    }

    public StudentBean2(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
    public void show() {
        System.out.println(name+"hello AOP");
    }
}
