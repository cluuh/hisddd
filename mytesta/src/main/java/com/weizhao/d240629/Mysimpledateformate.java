package com.weizhao.d240629;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 将日期转换成字符串格式 simpledateformate ，演示线程安全问题
 */
public class Mysimpledateformate {
    private static Logger logger = LoggerFactory.getLogger(Mysimpledateformate.class);

    private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public static void unsafe(){
        //将日期转换成字符串格式，演示线程安全问题
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //多线程下会报错,转换的日期也不对
                        logger.debug(String.valueOf(sdf.parse("1997-11-15")));

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            });
            thread.start();
        }
    }
    //解决方案1加锁解决
    public static void syncSafe(){
        //将日期转换成字符串格式，演示线程安全问题
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //解决方案1加锁解决
                    synchronized (sdf) {
                        try {
                            logger.debug(String.valueOf(sdf.parse("1997-11-15")));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        }
    }
    //解决方案2使用DataTtimeFormate
    public static void  unchangedatetime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd");
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LocalDate parse = LocalDate.parse("1997:11:15", dtf);
                    System.out.println(parse);
                }
            })    .start();
        }


    }
    public static void main(String[] args) {
//        unsafe();
//        syncSafe();
//        unchangedatetime();

        String aaa="sdfsfsf";
        int i = aaa.hashCode();
        System.out.println(i);

    }

}
