package com.like4u.AIM.ui.util;

public class SafeDoubleCheckSingleton {

    private volatile static SafeDoubleCheckSingleton singleton;
    private SafeDoubleCheckSingleton(){

    }
    public static SafeDoubleCheckSingleton getInstance(){
        if (singleton==null){
            synchronized (SafeDoubleCheckSingleton.class){
                if (singleton==null){
                    singleton=new SafeDoubleCheckSingleton();
                }
            }
        }return singleton;
    }
}