package main.base.ehAndLh;

public class SingletonSynM {

    private static SingletonSynM instance;

    private SingletonSynM(){}

    public static synchronized SingletonSynM getInstance(){

        if (instance == null){
            instance = new SingletonSynM();
        }

        return instance;
    }

}
