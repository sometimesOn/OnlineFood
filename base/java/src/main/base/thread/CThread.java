package main.base.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CThread  {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new CallThread());

        new Thread(futureTask).start();

        Integer result = futureTask.get();

        System.out.println(result);


    }



}

class CallThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        return new Random().nextInt(100);
    }
}
