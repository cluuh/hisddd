package com.weizhao.d240818;

import java.util.HashMap;
import java.util.Map;

public class MyStackOverflood {
    public static void main(String[] args) {
        HashMap<String, Integer> m = new HashMap<>();
        m.put("ab",1);
        digui(m);
    }

    private static void digui(Map<String,Integer> aa){
        Integer ab = aa.get("ab");
        System.out.println(ab);
        if(ab>900000000){
            return;
        }else {
            aa.put("ab",++ab);
            digui(aa);
        }
    }
}
