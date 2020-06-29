package com.sleepy.algorithm.thread.interview.ebay;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 商品数量
 *
 * @author gehoubao
 * @create 2020-06-29 10:05
 **/
public class GoodsFactory {
    Lock lock;
    Condition empty;
    Condition full;
    private String name;
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
            Thread.sleep(2000);
            while (num > 9) {
                full.await();
            }
            System.out.println(String.format("线程%s 生产商品『%s』一件，剩余 %s 件", Thread.currentThread().getName(), name, ++num));
            empty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}