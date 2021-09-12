package com.example.back.executor;

//import com.example.kotlininpractice.chapter03.Aq;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutor {

    public static void main(String[] args) throws Exception {
        TestExecutor e = new TestExecutor();
        new Thread(()->{
            e.testLock();
        }).start();
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Future<?> future = executor.submit(() -> {
//            System.out.println("-----");
//            System.out.println(Thread.currentThread().getName());
//            System.out.println("-----");
//        });
//        Object o = future.get();
////        executor.submit(() -> null);
//        System.out.println(o);
        synchronized (TestExecutor.class){
//            new Aq().isRight();
//            new Aq().setRight("");
        }
        e.testLock();
    }

    public synchronized void testLock() {
        try {
            System.out.println("---------------");
            Thread.sleep(10_000);
            System.out.println("+++++++++++++++");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
