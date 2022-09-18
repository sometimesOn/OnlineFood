package main.base.ehAndLh;

public class SingletonLHSynD {

    private static SingletonLHSynD instance;

    private SingletonLHSynD(){}

    public static SingletonLHSynD getInstance(){

        if( instance == null){

            synchronized (SingletonLHSynD.class){
                if (instance == null){
                    instance = new SingletonLHSynD();
                }
            }
        }

        return instance;

    }


}
