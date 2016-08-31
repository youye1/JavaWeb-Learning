package cn.youye.aopadvice.before;

import org.junit.Test;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Created by pc on 2016/8/19.
 */
public class TestBefore {

    @Test
    public void beforeTest(){

        Waiter waiter=new NativeWaiter();
        BeforeAdvice advice=new GreetingBeforeAdvice();
        ProxyFactory factory=new ProxyFactory();
        factory.setTarget(waiter);
        factory.addAdvice(advice);
        Waiter proxy = (Waiter) factory.getProxy();

        proxy.greetTo("John");

        proxy.serveTo("Mary");
    }
}
