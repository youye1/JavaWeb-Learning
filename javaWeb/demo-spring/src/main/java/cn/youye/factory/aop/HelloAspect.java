package cn.youye.factory.aop;

/**
 * Created by pc on 2016/7/12.
 */
public class HelloAspect {
    //前置通知
    public void beforeAdvice() {
        System.out.println("===========before advice");
    }

    //后置通知
    public void afterAdvice() {
        System.out.println("===========after advice");
    }
}
