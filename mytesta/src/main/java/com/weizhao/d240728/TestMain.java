package com.weizhao.d240728;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static final  Logger logger = LoggerFactory.getLogger(TestMain.class);
    public static void main(String[] args) {

        MyLock lock = new MyLock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.tryLock();
                try {
                    logger.info("0加锁成功");
                   // lock.tryLock();
                }finally {
                    lock.unlock();
                }
            }
        },"thread").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.tryLock();
                try {
                    logger.info("1加锁成功");
                    // lock.tryLock();
                }finally {
                    lock.unlock();
                }


                System.out.println("重入锁成功");
            }
        },"thread1").start();
        System.out.println("main");
    }
}
