package cn.youye.factory.bean;

/**
 * 使用实体工厂
 * Created by pc on 2016/7/12.
 */
public class HelloInstanceFactory {

    public HelloDao newInstance(String message) {
        return new HelloDaoImpl(message);
    }
}
