package com.lokal.lokaltest.library;

/**
 * Created by ADMIN on 26-06-2018.
 */
public class ExceptionTracker {

    public static void track(Exception exception) {
        exception.printStackTrace();
    }

    public static void track(String message) {
        //Log.e();
    }
}