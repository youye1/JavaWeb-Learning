package cn.youye.multiThread;

/**
 * Created by pc on 2016/9/14.
 */
public class MyThread2 extends Thread {

    protected int countDown = 5;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public MyThread2() {
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "MyThread2!") + "),";
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
