package com.weizhao.d240725;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程饥e现象，以及处理
 */
public class mypoolhunger {
    private static final  List<String> menus = Arrays.asList("爆炒猪肝", "青椒炒肉", "宫保鸡丁");
    public static void main(String[] args) {

        hungerTest();
    }

    /**
     * 线程池的饥饿现象演示
     */
    private static void hungerTest() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(() -> {
            System.out.println("1通知取餐");
            Random random = new Random();
            int i1 = random.nextInt(3);//(0~2)
            try {
                String s = pool.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("烹饪中...");
                        return cookMenus(i1);
                    }
                }).get();
                System.out.println("1取到美食："+s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            System.out.println("2通知取餐");
            Random random = new Random();
            int i1 = random.nextInt(3);//(0~2)
            try {
                String s = pool.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("烹饪中...");
                        return cookMenus(i1);
                    }
                }).get();
                System.out.println("2取到美食："+s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("ok");
        //pool.shutdown();
    }

    private static String cookMenus(int i){
        String res="没有该菜品";
        if(i>0 || i<2){
            res = menus.get(i);
        }
        return res;
    }
}
