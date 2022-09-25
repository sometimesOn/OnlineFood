package main.base.thread;

public class EThread extends Thread{

    public static void main(String[] args) {

        EThread eThread = new EThread();


        eThread.start();

    }

    @Override
    public void run() {
        System.out.println("继承Thread创建");
    }
}
