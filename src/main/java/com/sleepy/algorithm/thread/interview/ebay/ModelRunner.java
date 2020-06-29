package com.sleepy.algorithm.thread.interview.ebay;

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

        ExecutorService service = getThreadPool();
        for (int j = 0; j < 4; j++) {
            service.execute(() -> {
                for (int i = 0; i < 10; i++) {
                    goodsFactory.consume();
                }
            });
        }
        service.execute(() -> {
            for (int i = 0; i < 40; i++) {
                goodsFactory.produce();
            }
        });
        service.shutdown();
    }

    private static ExecutorService getThreadPool() {
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        return new ThreadPoolExecutor(6, 6, 0, TimeUnit.SECONDS, workQueue, (ThreadFactory) Thread::new);
    }
}