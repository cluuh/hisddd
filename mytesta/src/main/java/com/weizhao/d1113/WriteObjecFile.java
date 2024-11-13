package com.weizhao.d1113;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * 运用反射将任意对象写到文件中
 */
public class WriteObjecFile {

    public static void main(String[] args) {
        Student zhansan = new Student("zhansan", 27);
        saveObj(zhansan);
    }
    public static void saveObj(Object o){
        Class<?> aClass = o.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        HashMap<String, Object> kv = new HashMap<>();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            try {
                Field field = aClass.getDeclaredField(name);
                field.setAccessible(true);
                Object o1 = field.get(o);
                kv.put(name,o1+"");
            } catch (Exception e) {
                System.out.println("获取对象的值异常");
                e.printStackTrace();
            }
        }
        System.out.println(kv);
    }
}
