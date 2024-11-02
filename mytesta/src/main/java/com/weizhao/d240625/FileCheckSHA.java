package com.weizhao.d240625;

import org.apache.commons.codec.digest.DigestUtils;



import java.io.*;
import java.util.Random;

/**
 * sha文件完整性校验
 *
 * @author xxx
 */
public class FileCheckSHA {

    public static void main(String[] args) throws IOException {



//        ExecutorService executorService = Executors.newFixedThreadPool(300);
        for(int i=0;i<800;i++){
            int finalI = i;
           new Thread(new Runnable() {
                @Override
                public void run() {
                    for (; ; ) {
                        String adminStr = "MXhJSetJOiAt4Ek6II8QgTIHjTAMEf5+HQbSAWkBT9IBaaHHxYE68Q3AhYHhyEECSAt4kg5IC3gVrEkbN5cAEAw3Gg/CWsCTdEBawJN0AfLcGTADADQBT35jBJ6KA9ICnqQOA0RLpgrGAJ0Q+eB3bCQdkBbwJB2QbtyoDJphClBJGBfqSOiAtIAn6YC0wOKFFOF4YwHAAHvjRuSkBTxJB6QFPAHwOsCnWwLgR4v1YmAi4Ek6IC3gSTpOm/LQElIAkogqryab";
                        adminStr = getStrRadom(adminStr.length());
                        File file = new File("D:\\temp\\aa\\16.201.9.86.3c2v" + finalI);
                        boolean moban = moban(adminStr, file);
                        if (moban) {
                            System.out.println(file.getName());
                            break;
                        }
                        try {
                            Thread.sleep(3l);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },""+finalI).start();
        }

    }

    /**
     * 从内嵌字符串中获取指定长度的随机字符串
     * @param strSize
     * @return
     */
    public static String getStrRadom(int strSize){

        Object o = new Object();
        String sed="zxcvbnmasdfghjklqwertyuiop/+ZXCVBNMASDFGHJKLQWERTYUIOP1234567890";
        char[] chars = sed.toCharArray();
        int num=chars.length;
        Random random = new Random();

        StringBuilder b = new StringBuilder();
        for(int j=0;j<strSize;j++){
            int i = random.nextInt(num-1);
            b.append(chars[i]);
        }
        return b.toString();

    }

    public static  boolean moban(String adminStr,File outFile){
        String  userStr="MnhJSYO0gCfpgLSAp+4U3fsGQOL7dRgW8CQdkBbwJB06AD4uARgQjhwknqQD0gKepAPiJM+kGQCjj/qDkHRAWsCTdEBaRANVwTgAEU9+YwxIC3iSDkgLeFMOjDINQOS/37BpAU/SAWkBTxIb6aHGAIZxoU4O4Ek6IC3gSTpAeD7dEgAw3rgROUkHpAU8SQekyZZcRQpAk/ViYOqAtIAn6YC0AEdlXDTCIKS8mmw=";
        StringBuilder builder = new StringBuilder();
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<hasp_info>\n" +
                "  <host_fingerprint type=\"SL-AdminMode\" crc=\"2135536948\">")
                .append(adminStr)
                .append("</host_fingerprint>\n" +
                        "  <host_fingerprint type=\"SL-UserMode\" vendorid=\"85015\" crc=\"1925299304\">")
                .append(userStr)
                .append("</host_fingerprint>\n" +
                        "</hasp_info>\n" +
                        "\n");

        //1写文件
        writeFile(outFile,builder.toString());
        //2生成sha256
        String s = fileSHA256(outFile);
//        System.out.println(s);
        //3核对
//        String name = Thread.currentThread().getName();
//        System.out.println(name+":"+s);
//        System.out.println();
//        &&
        if(s.endsWith("fcd2a1dB60984c")){
            return true;
        }
        return false;
    }


    public static void writeFile(File file ,String writStr){

        try {
            boolean exists = file.exists();
            if(!exists){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.write(writStr);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("写入失败");
            e.printStackTrace();
        }
    }


    /**
     * 获取文件SHA256
     *
     * @param file
     * @return
     */
    public static String fileSHA256(File file) {
        String sha256Hex = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            sha256Hex = DigestUtils.sha256Hex(inputStream);
            return sha256Hex;
        } catch (IOException e) {
            System.out.println("文件获取SHA256失败");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
