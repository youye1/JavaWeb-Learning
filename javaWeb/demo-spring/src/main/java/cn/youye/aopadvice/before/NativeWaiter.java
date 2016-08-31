package cn.youye.aopadvice.before;

/**
 * 2.接口实现类
 */
public class NativeWaiter implements Waiter {

    @Override
    public void greetTo(String name) {
        System.out.println("greet to "+name);
    }

    @Override
    public void serveTo(String name) {
        System.out.println("serving "+name);

    }
}
