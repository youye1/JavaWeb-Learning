package cn.youye.aop.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib代理类
 * Created by pc on 2016/8/18.
 */
public class CglibProxyFactory implements MethodInterceptor {

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object createStudent(Object object) {
        this.object = object;
        //使用Enhancer类创建代理对象,
        Enhancer enhancer = new Enhancer();
        //这样就为实例指定一个父类,即目标对象
        enhancer.setSuperclass(object.getClass());
        //指定一个回调函数
        enhancer.setCallback(this);
        //新创建出来的就是目标对象的子类
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method,
                            Object[] objects, MethodProxy methodProxy)
            throws Throwable {

        StudentBean2 stu = (StudentBean2) object;
        Object result = null;
        if (stu.getName() != null) {
            result = methodProxy.invoke(object, objects);
        } else {
            System.out.println("名称为空,该方法被拦截");
        }
        return result;
    }
}
