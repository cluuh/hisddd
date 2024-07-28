package com.weizhao.d240725;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 计划线程池基本用法
 */
public class myscheduleThreadPool {

    private static  final  Logger logger = LoggerFactory.getLogger(myscheduleThreadPool.class);
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        pool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
               logger.info("1执行了");

            }
        },5,5,TimeUnit.SECONDS);
        pool.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("2执行了");

            }
        },5,5,TimeUnit.SECONDS);


    }

    /**
     * 计划线程池基本用法
     */
    private static void scheduledBasicUse() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);

        pool.schedule(() -> {System.out.println("1我待会再处理");
            try {
                Thread.sleep(3);
                System.out.println("1我处理l");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },5, TimeUnit.SECONDS);
        pool.schedule(() -> System.out.println("2我待会再处理"),6, TimeUnit.SECONDS);
        pool.schedule(() -> System.out.println("3我待会再处理"),5, TimeUnit.SECONDS);
        pool.shutdown();
    }
}
