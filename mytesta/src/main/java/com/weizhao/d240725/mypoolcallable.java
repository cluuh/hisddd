package com.weizhao.d240725;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 批量提交线程池的api
 */
public class mypoolcallable {
    private static final Logger logger = LoggerFactory.getLogger(mypoolcallable.class);
    public static void main(String[] args) {
        getfasterRes();

    }



    /**
     * 批量提交线程池
     */
    private static void batchsubmit() {
        long l1 = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(3);
        ArrayList<Callable<String>> callables = new ArrayList<>();
        callables.add(() -> {
            logger.info("1start");
            Thread.sleep(2000L);
            return "1";
        });
        callables.add(() -> {
            logger.info("2start");
            Thread.sleep(5000L);
            return "2";
        });
        callables.add(() -> {
            logger.info("3start");
            Thread.sleep(3000L);
            return "3";
        });
        try {
            List<Future<String>> futures = pool.invokeAll(callables);
            futures.parallelStream().forEach(
                    future->{
                        try {
                            String s1 = future.get();
                            logger.info(s1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        System.out.println((l2-l1));
        pool.shutdown();
    }
    /**
     * 获取批量提交线程中最快执行结束的线程结果
     */
    private static void getfasterRes() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        ArrayList<Callable<String>> callables = new ArrayList<>();
        callables.add(() -> {
            logger.info("1start");
            Thread.sleep(2000L);
            return "1";
        });
        callables.add(() -> {
            logger.info("2start");
            Thread.sleep(5000L);
            return "2";
        });
        callables.add(() -> {
            logger.info("3start");
            Thread.sleep(1000L);
            return "3";
        });
        try {
            String s = pool.invokeAny(callables);
            logger.info("最快执行的结果："+s);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
