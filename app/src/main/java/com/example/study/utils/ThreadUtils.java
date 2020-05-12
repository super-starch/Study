package com.example.study.utils;

import android.os.Handler;

public class ThreadUtils {
    /**子线程执行ask*/
    public static void runThread(Runnable task)
    {
        new Thread(task).start();
    }
    //主线程handler
    public static Handler mhandler=new Handler(){};

    /**UI线程执行ask*/
    public static void runUIThread(Runnable task)
    {
        mhandler.post(task);
    }
}
