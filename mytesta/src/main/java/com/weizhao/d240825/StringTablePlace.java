package com.weizhao.d240825;

import java.util.ArrayList;

public class StringTablePlace {
    public static void main(String[] args) {
        int j=0;
        String a="sd";
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 260000; i++) {
          a+="sdfdff";
           list.add(a);
           sleep(5L);
           j++;
        }
    }
    public static void sleep (Long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
