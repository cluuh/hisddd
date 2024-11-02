package com.weizhao.d240818;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SayHelloHeap {
    public static void main(String[] args) throws InterruptedException {
        long aa=1;
        long bb=2;
        long cc=0;
//        ArrayList<String> strings  = new ArrayList<>();
//        String aaa="123";
        ArrayList<Integer> hehe = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 26; i++) {
            cc+=bb;
            bb*=2;
            Thread.sleep(1000L);
            if(i==25){
                cc+=bb;
            }
            if(i>10)
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int j = 0; j < 1000; j++) {
                            hehe.add(123);
                        }
                        Thread.sleep(30000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        System.out.println(bb);
        System.out.println(cc);
    }
}
