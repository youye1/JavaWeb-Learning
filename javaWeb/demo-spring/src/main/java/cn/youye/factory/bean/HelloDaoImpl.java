package cn.youye.factory.bean;

import cn.youye.factory.bean.HelloDao;

/**
 * Created by pc on 2016/8/8.
 */
public class HelloDaoImpl implements HelloDao {

    private String message;
    /**
     * 空构造方法
     */
    public HelloDaoImpl() {
        this.message = "hello Spring";
    }

    /**
     * 带参构造方法
     */
    public HelloDaoImpl(String message) {
        this.message = message;
    }

    @Override
    public void sayHello() {
        System.out.println(message+"|--|");
    }
}
