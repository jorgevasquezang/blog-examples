package com.jovasquez.test;

/**
 * Created by jvasquez on 9/25/2016.
 */
public interface DefaultTest {

    default void hello(){
        System.out.print("Hello default interface");
    }
}
