package cn.youye.multiThread;

/**
 * 实现倒计时功能
 * Created by pc on 2016/9/14.
 */
public class MyThread1 implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public MyThread1() {
    }

    public String status() {
        return "---" + id + "(" + (countDown > 0 ? countDown : "MyThread1!") + "),";
    }

    @Override
    public void run() {
        System.out.println("This is " + getClass().getSimpleName() + " run()");
        while (countDown-->0){
            System.out.println(status());
            Thread.yield();
        }

    }
}
