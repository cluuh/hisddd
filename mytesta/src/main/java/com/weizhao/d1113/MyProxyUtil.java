package com.weizhao.d1113;

import com.weizhao.d1113.impl.YangStar;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理工具
 */
public class MyProxyUtil {
    public static void main(String[] args) {
        YangStar yangStar = new YangStar();
        StarProxyServer proxyServer = createProxyServer(yangStar);

        String dance = proxyServer.dance();
        System.out.println(dance);
    }
    public static StarProxyServer createProxyServer(StarProxyServer starProxyServer){
        StarProxyServer o = (StarProxyServer)Proxy.newProxyInstance(
                MyProxyUtil.class.getClassLoader(),
                new Class[]{StarProxyServer.class}
                ,new InvocationHandler(){
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Class<?> aClass = proxy.getClass();
                        String name = aClass.getName();
                        System.out.println(name);
                        long l = System.currentTimeMillis();
                        Object invoke = method.invoke(starProxyServer, args);
                        Thread.sleep(10L);
                        long l1 = System.currentTimeMillis();
                        System.out.println( "执行耗时"+(l1-l));
                        return invoke;
                    }
                });
        return o;
    }

}
