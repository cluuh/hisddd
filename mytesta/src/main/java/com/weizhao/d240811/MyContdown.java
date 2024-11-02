package com.weizhao.d240811;

import java.util.Random;

public class MyContdown {
    public static void main(String[] args) {
        int addRate[] = new int[5];
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                for (int i1 = 0; i1 < 50; i1++) {
                    addRate[finalI] += 2;
                    String temprate="\r";
                    for (int i2 = 0; i2 < addRate.length; i2++) {
                        temprate+="玩家"+i2+":"+addRate[i2]+"% ";
                    }
                    System.out.print(temprate);
                    try {
                        int sleepTemp = random.nextInt(100);
                        Thread.sleep(sleepTemp);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
