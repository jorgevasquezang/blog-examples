package com.jovasquez.singleton.second;

/**
 * Created by jvasquez on 9/9/2016.
 */
public class MySingleton {

    private volatile static MySingleton mySingleton;


    private MySingleton() {
    }

    public static synchronized MySingleton getInstance() {

        if (mySingleton == null) {
            mySingleton = new MySingleton();
        }
        return mySingleton;

    }

    public static MySingleton getInstance2() {
        if (mySingleton == null) {
            synchronized (mySingleton) {
                if (mySingleton == null) {
                    mySingleton = new MySingleton();
                }
            }
        }
        return mySingleton;
    }

}
