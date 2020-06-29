package com.sleepy.algorithm.thread.interview.ebay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 商品
 *
 * @author gehoubao
 * @create 2020-06-29 10:05
 **/
public class GoodsFactory {
    /**
     * 商品读写锁
     */
    Lock lock;
    /**
     * 商品为空监视器
     */
    Condition empty;
    /**
     * 商品已满监视器
     */
    Condition full;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品库存
     */
    private Integer num;

    public GoodsFactory(String name) {
        this.name = name;
        this.num = 0;
        this.lock = new ReentrantLock();
        this.empty = lock.newCondition();
        this.full = lock.newCondition();
    }

    public void produce() {
        lock.lock();
        try {
            while (num > 9) {
                full.await();
            }
            System.out.println(String.format("线程%s 生产商品『%s』一件，剩余 %s 件", Thread.currentThread().getName(), name, ++num));
            empty.signal();
        } catch (InterruptedException e) {
            System.out.printf("生产异常，" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void consume() {
        lock.lock();
        try {
            while (num < 1) {
                empty.await();
            }
            System.out.println(String.format("线程%s 消费商品『%s』一次，剩余 %s 件", Thread.currentThread().getName(), name, --num));
            full.signalAll();
        } catch (InterruptedException e) {
            System.out.printf("消费异常，" + e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}