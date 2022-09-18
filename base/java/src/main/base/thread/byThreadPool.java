package main.base.thread;

import java.util.concurrent.*;

public class byThreadPool implements Runnable{
    @Override
    public void run() {

        System.out.println("通过线程池创建");

    }


    public static  void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {

            threadPool.execute(new byThreadPool());
            System.out.println("  "  + i);

        }

        threadPool.shutdown();


    }

}
