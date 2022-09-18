package main.base.thread;

public class EThread extends Thread{

    public static void main(String[] args) {

        EThread thread = new EThread();

        thread.start();

    }

    @Override
    public void run() {
        System.out.println("继承Thread创建");
    }
}
