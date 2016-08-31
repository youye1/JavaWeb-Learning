package cn.youye.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工厂类
 * Created by pc on 2016/8/18.
 */
public class ProxyFactory implements InvocationHandler {

    private Object stu;

    //创建StudentBean的代理类
    public Object createStudentProxy(Object stu) {
        this.stu = stu;

        //参数详解：
        //1.classLoader：目标对象的类加载器
        //2.interface：目标对象实现的接口
        //3.this:传入一个InvocationHandler实例
        return Proxy.newProxyInstance(stu.getClass().getClassLoader(),
                stu.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        StudentBean s = (StudentBean) stu;

        Object object = null;
        if (s.getName()!=null){
            //通过代理返回一个新的对象，即object
            object=method.invoke(stu,args);
        }else {
            System.out.println("名称为空，代理类已拦截");
        }

        return object;
    }
}
