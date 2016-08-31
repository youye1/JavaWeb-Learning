package cn.youye.aop.proxy;

/**
 * 接口实现类
 * Created by pc on 2016/8/18.
 */
public class StudentBean implements StudentInte {
    private String name;

    public StudentBean() {
    }

    public StudentBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        System.out.println("hello AOP");
    }
}
