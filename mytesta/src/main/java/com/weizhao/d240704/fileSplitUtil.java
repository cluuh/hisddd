package com.weizhao.d240704;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class fileSplitUtil {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\chenwz\\Desktop\\test\\mybatis.zip");
        splitFile(file,10240);
    }
    public static List<File> splitFile(File file ,int blockSize){
        try {
            ArrayList<Byte[]> bytes1 = new ArrayList<>();
            List<File> files = new ArrayList<>();
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes=new byte[10240];
            int len;
            while ((len=fileInputStream.read(bytes))!=-1){

            }

            return files;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
