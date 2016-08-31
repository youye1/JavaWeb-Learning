package cn.youye.aop.cglib;


/**
 * Created by pc on 2016/8/18.
 */
public class CGLibTest {
    public static void main(String[] args) {

        StudentBean2 stu1 = (StudentBean2) (new CglibProxyFactory()
                .createStudent(new StudentBean2()));
        StudentBean2 stu2 = (StudentBean2) (new CglibProxyFactory()
                .createStudent(new StudentBean2("youye ")));
        stu1.show();
        stu2.show();

        int x=6140;
        int y=x/1000;
        System.out.println(y);
    }
}
