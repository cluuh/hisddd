package com.weizhao.d240629.connectionDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 模仿数据库连接池
 */
public class MyPool {

    private static final Logger log=LoggerFactory.getLogger(MyPool.class);


    private final  int size;
    private Connection[] connections;

    //数据库连接池对象的状态 ，0表示空闲 ，1表示繁忙
    private AtomicIntegerArray status;


    //初始化数据库连接池
    //连接对象是模拟的情况
    public MyPool(int size) {

        this.size = size;
        this.connections =new Connection[size];
        this.status = new AtomicIntegerArray(new int[size]);
        for (int i = 0; i < size; i++) {
            //虚假的连接对象这样创建，interface接口
            connections[i]= new MockConnection();
        }
    }
    //从池子中获取连接对象
    public Connection borrow(){
        for (int i = 0; i < size; i++) {
            if(status.get(i)==0){
                if(status.compareAndSet(i,0,1)){
                    return connections[i];
                }
            }
        }
        //没有空闲的线程 进入等待策略
        try {
            String name = Thread.currentThread().getName();
            log.info(name+" is in wait....");
            this.wait();

            //被唤醒后再次尝试获取
            borrow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //归还线程池对象
    public void backConnection(Connection connection){
        for (int i = 0; i < size; i++) {
            if(this.connections[i].equals(connection)){
                status.set(i,0);
                synchronized (this){
                    this.notifyAll();
                }
                break;
            }
        }

    }

    //释放线程池对象
    public void close(){

    }
}
