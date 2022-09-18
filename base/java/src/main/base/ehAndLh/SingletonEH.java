package main.base.ehAndLh;

public class SingletonEH {

    private static SingletonEH instance = new SingletonEH();
    private SingletonEH(){}

    public static SingletonEH getInstance(){
        return instance;
    }

}
