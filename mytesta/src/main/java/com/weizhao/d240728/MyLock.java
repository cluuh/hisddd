package com.weizhao.d240728;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义锁 基于aqs实现
 * 不可重入锁
 */
public class MyLock implements Lock {

    private MyAsync myAsync=null;

    //同步器类 独占锁实现
    class MyAsync extends AbstractQueuedSynchronizer {
        //尝试获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //尝试释放锁
        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //判断是否持有锁
        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        public Condition newCondition(){
            return new ConditionObject();
        }
    }

    public MyLock() {
        this.myAsync = new MyAsync();
    }

    //阻塞式加锁
    @Override
    public void lock() {
        myAsync.tryAcquire(1);
    }

    //加锁可打断
    @Override
    public void lockInterruptibly() throws InterruptedException {
        myAsync.acquireInterruptibly(1);
    }

    //尝试加锁 加锁不成功返回false
    @Override
    public boolean tryLock() {
        try {
            myAsync.acquireInterruptibly(1);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;

    }

    //带超时的加锁
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return myAsync.tryAcquireNanos(1, unit.toNanos(time));
    }

    //解锁
    @Override
    public void unlock() {
        myAsync.tryRelease(1);
    }

    //创建条件变量
    @Override
    public Condition newCondition() {
        return null;
    }
}
