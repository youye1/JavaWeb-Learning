package cn.youye.aop.spring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 2.创建切面类
 * Created by pc on 2016/8/18.
 */
@Aspect
public class StuInterceptor {


    /**
     * 打印方法AOP
     */
    @Pointcut("execution(* cn.youye.aop.spring.Student.show(..))")
    // @Pointcut("execution(* cn.youye.aop.spring.Student.*(..))")
    public void printMethod() {
    }

    //前置通知
    @Before("printMethod()")
    public void printBeforeAdvice() {
        System.out.println("printBeforeAdvice()!");
    }
    //后置通知
    @AfterReturning(pointcut = "printMethod()", returning = "flag")
    public void printAfterAdvice(String flag) {
        System.out.println("printAfterAdvice()! " + flag);
    }
    //最终通知
    @After("printMethod()")
    public void finallyAdvice() {
        System.out.println("finallyAdvice()!");
    }
    //环绕通知
    @Around("printMethod() && args(name)")
    public Object printAroundAdvice(ProceedingJoinPoint pjp, String name)
            throws Throwable {
        Object result = null;
        if (name.equals("youye"))
            pjp.proceed();
        else
            System.out.println("showt()方法以及被拦截...");
        return result;
    }

}
