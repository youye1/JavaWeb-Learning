package cn.youye.factory.aop;


import cn.youye.factory.bean.HelloDao;

/**
 * Created by pc on 2016/7/12.
 */
public class HelloDaoImpl implements HelloDao {
    @Override
    public void sayHello() {
        System.out.println("=========hello----------");
    }
}
