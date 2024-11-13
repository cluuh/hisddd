package com.weizhao.d1113;

import java.lang.reflect.Proxy;

public class myAnnotationParse {

    public static void main(String[] args) {
        Student student = new Student();
        getClassAnnotation(student);
    }

    public static void getClassAnnotation(Object o){
        Class<?> aClass = o.getClass();
        boolean annotationPresent = aClass.isAnnotationPresent(Mylog.class);
        if(annotationPresent){
            Mylog declaredAnnotation = aClass.getDeclaredAnnotation(Mylog.class);
            String s = declaredAnnotation.dataStr();
            String mokuai = declaredAnnotation.mokuai();
            System.out.println(s);
            System.out.println(mokuai);
        }
    }

}
