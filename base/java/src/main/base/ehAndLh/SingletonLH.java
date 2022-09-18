package main.base.ehAndLh;

public class SingletonLH {

    private static SingletonLH instance;

    private SingletonLH(){}

    public static SingletonLH getInstance(){

        if (instance == null){

            instance =  new SingletonLH();
        }

        return instance;
    }

}
