package com.bodhi.framecore.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2018/11/23 18:18
 * desc :
 */
public class ThreadManager {

    private static ThreadManager threadManager;

    private ThreadManager(){}

    public static ThreadManager getInstance(){
        if (threadManager==null) {
            threadManager=new ThreadManager();
        }

        return threadManager;
    }

    private ScheduledExecutorService scheduledExecutorService;

    public void run(Runnable runnable){
        if (scheduledExecutorService==null) {
            scheduledExecutorService= Executors.newScheduledThreadPool(10);
        }

        if (runnable==null) {
            return;
        }

        scheduledExecutorService.submit(runnable);
    }

    public void runDelayed(Runnable runnable,long delay){
        if (scheduledExecutorService==null) {
            scheduledExecutorService =Executors.newScheduledThreadPool(10);
        }

        if (runnable==null) {
            return;
        }

        scheduledExecutorService.schedule(runnable,delay, TimeUnit.MILLISECONDS);
    }

    /**
     *线程睡眠
     */
    public void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
