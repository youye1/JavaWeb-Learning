package cn.youye.multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pc on 2016/9/14.
 */
public class MainTest {

    public static void main(String[] args) {

//        commonTest();
//        singleTest();
//        fixedTest();
//        cacheTest();
        scheduledTest();
    }

    /**
     * 测试runnable线程和Thread线程
     */
    public static void commonTest() {
        MyThread1 myThread1 = new MyThread1();
        myThread1.run();

        Thread thread = new Thread(new MyThread1());
        thread.start();
//        System.out.println("Waiting for MyThread1");
        for (int i = 0; i < 5; i++) {
            new Thread(new MyThread1()).start();
            new MyThread2().start();
            System.out.println("Waiting for MyThread1");
            System.out.println("Waiting for MyThread2");
        }
    }

    /**
     * 单线程的线程池测试
     */
    public static void singleTest() {

        //创建一个单线程池
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Thread thread1 = new Thread(new MyThread1());
        Thread thread2 = new Thread(new MyThread1());
        Thread thread3 = new Thread(new MyThread1());
        Thread thread4 = new Thread(new MyThread1());

        //将线程放入线程池中执行
        pool.execute(new MyThread1());
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        //线程池关闭方式
//        pool.shutdown();
        pool.shutdownNow();
    }

    /**
     * 测试固定数量的线程池
     */
    private static void fixedTest() {
        //创建一个固定数量的线程池
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Thread thread1 = new Thread(new MyThread1());
        Thread thread2 = new Thread(new MyThread1());
        Thread thread3 = new Thread(new MyThread1());
        Thread thread4 = new Thread(new MyThread1());

        //将线程放入线程池中执行
        pool.execute(new MyThread1());
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        //线程池关闭方式
//        pool.shutdown();
        pool.shutdownNow();
    }

    /**
     * 测试可缓存的线程池
     */
    private static void cacheTest() {
        //创建一个可缓存的线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        Thread thread1 = new Thread(new MyThread1());
        Thread thread2 = new Thread(new MyThread1());
        Thread thread3 = new Thread(new MyThread1());
        Thread thread4 = new Thread(new MyThread1());

        //将线程放入线程池中执行
        pool.execute(new MyThread1());
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        //线程池关闭方式
        pool.shutdown();
//        pool.shutdownNow();
    }

    /**
     * 测试无限制的线程池
     */
    private static void scheduledTest() {
        //创建一个可缓存的线程池
        ExecutorService pool = Executors.newScheduledThreadPool(2);
        Thread thread1 = new Thread(new MyThread1());
        Thread thread2 = new Thread(new MyThread1());
        Thread thread3 = new Thread(new MyThread1());
        Thread thread4 = new Thread(new MyThread1());

        //将线程放入线程池中执行
        pool.execute(new MyThread1());
        pool.execute(thread1);
        pool.execute(thread2);
        pool.execute(thread3);
        pool.execute(thread4);
        //线程池关闭方式
//        pool.shutdown();
        pool.shutdownNow();
    }


}
