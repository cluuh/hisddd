package com.weizhao.d1113;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Myreflect {
    public static void main(String[] args) throws ClassNotFoundException {

        //获取类的class对象
        //Class.forname(“类名”)
        //类。CLass
        //对象。getClass()方法
        Class<?> aClass = Class.forName("com.weizhao.d1113.Student");
        Class aClass1=Student.class;
        Class<? extends Student> aClass2 = new Student().getClass();

        Constructor<?> aconstructor = null;
        try {
            aconstructor = aClass.getDeclaredConstructor(String.class);
            aconstructor.setAccessible(true);
            //创建了一个对象
            Student astu =(Student) aconstructor.newInstance("为找");

            //获取成员变量
            Field name = aClass1.getDeclaredField("name");
            name.setAccessible(true);
            name.set(astu,"weizhao");
            Object o = name.get(astu);
            System.out.println(o);

            Method getName = aClass.getDeclaredMethod("getName");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
