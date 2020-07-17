package com.sleepy.algorithm.example;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * level1
 *
 * @author gehoubao
 * @create 2020-06-29 9:59
 **/
public class ThreadProblemExample {

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            lock1.lock();
            lock2.lock();
            System.out.println("thread - 1 running");
            lock2.unlock();
            lock1.unlock();
        });
        Thread thread2 = new Thread(() -> {
            lock2.lock();
            lock1.lock();
            System.out.println("thread - 2 running");
            lock1.unlock();
            lock2.unlock();
        });
        thread1.start();
        thread2.start();
    }

    private static void inconsistentData() {
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap<>(4);
            map.put("test", "test" + i);
            String s = map.toString();
            Thread thread = new Thread(() -> {
                System.out.println(map);
            });
            thread.start();
        }
    }

}