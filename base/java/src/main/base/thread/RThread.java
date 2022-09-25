package main.base.thread;

public class RThread implements Runnable{

    @Override
    public void run() {
        System.out.println("实现Runnable接口");
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new RThread());

        thread.start();

        long id = thread.getId();


    }

}
