package com.jovasquez.singleton.first;

/**
 * Created by jvasquez on 9/9/2016.
 */
public class MySingleton {

    private static MySingleton mySingleton;


    private MySingleton() {
    }

    public static MySingleton getInstance() {
        if (mySingleton == null) {
            mySingleton = new MySingleton();
        }
        return mySingleton;
    }

}
