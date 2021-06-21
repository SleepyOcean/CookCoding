package com.sleepy.algorithm.thread;

import java.util.concurrent.*;

/**
 * 模型启动器
 *
 * @author gehoubao
 * @create 2020-06-29 10:08
 **/
public class ModelRunner {

    public static void main(String[] args) {
        threadPoolWay();
    }

    private static void threadWay() {
    }

    private static void threadPoolWay() {
        GoodsFactory goodsFactory = new GoodsFactory("Xbox One");

        // 创建线程池
        ExecutorService service = getThreadPool();

        // 创建 4个 消费线程
        for (int j = 0; j < 4; j++) {
            service.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        System.out.println("消费线程出现'中断异常'，" + e.getMessage());
                    }
                    goodsFactory.consume();
                }
            });
        }

        // 创建 2个 生产者线程
        for (int j = 0; j < 2; j++) {
            service.execute(() -> {
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println("生产线程出现'中断异常'，" + e.getMessage());
                    }
                    goodsFactory.produce();
                }
            });
        }

        service.shutdown();
    }

    private static ExecutorService getThreadPool() {
        SynchronousQueue<Runnable> workQueue = new SynchronousQueue<>();
        return new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, workQueue, (ThreadFactory) Thread::new);
    }
}