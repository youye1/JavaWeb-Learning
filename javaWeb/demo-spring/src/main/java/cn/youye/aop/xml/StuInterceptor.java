package cn.youye.aop.xml;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 2.创建切面类
 * Created by pc on 2016/8/18.
 */

public class StuInterceptor {

    /**
     * 打印方法AOP
     */
    public void printMethod() {
    }

    //前置通知
    public void printBeforeAdvice() {
        System.out.println("printBeforeAdvice()!");
    }

    //后置通知
    public void printAfterAdvice(String flag) {
        System.out.println("printAfterAdvice()! " + flag);
    }

    //最终通知
    public void finallyAdvice() {
        System.out.println("finallyAdvice()!");
    }

    //环绕通知
    public Object printAroundAdvice(ProceedingJoinPoint pjp, String name)
            throws Throwable {
        Object result = null;
        if (name.equals("youye"))
            pjp.proceed();
        else
            System.out.println("show()方法以及被拦截...");
        return result;
    }

}
