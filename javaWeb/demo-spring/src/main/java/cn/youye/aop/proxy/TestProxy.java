package cn.youye.aop.proxy;

/**
 * 测试入口类
 * Created by pc on 2016/8/18.
 */
public class TestProxy {

    public static void main(String[] args) {
        // StudentBean stu1=new StudentBean("Youye");
        StudentBean stu1 = new StudentBean();
        ProxyFactory factory = new ProxyFactory();

        StudentInte st = (StudentInte) factory.createStudentProxy(stu1);
        st.show();
    }
}
