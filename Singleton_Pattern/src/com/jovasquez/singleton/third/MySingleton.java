package com.jovasquez.singleton.third;

/**
 * Created by jvasquez on 9/9/2016.
 */
public class MySingleton {

    private static MySingleton mySingleton = new MySingleton();

    public static MySingleton getInstance() {
        return mySingleton;
    }
}
