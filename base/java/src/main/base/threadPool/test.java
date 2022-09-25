package main.base.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class test {

//    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        WaitNotifyExample example = new WaitNotifyExample();
//        executorService.execute(() -> example.after());
//        executorService.execute(() -> example.before());
//
//
//    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AwaitSignalExample example = new AwaitSignalExample();
        executorService.execute(() -> example.after());
        executorService.execute(() -> example.before());
    }


}
