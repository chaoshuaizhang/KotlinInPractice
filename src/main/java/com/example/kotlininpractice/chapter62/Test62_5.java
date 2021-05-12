package com.example.kotlininpractice.chapter62;

public class Test62_5 {
    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                int i = 0;
                try {
                    while (true) {
                        Thread.sleep(500);
                        System.out.println(i++ + "  " + isInterrupted());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        try {
            Thread.sleep(2000);
            System.out.println("---------------------------------");
            thread.interrupt();
            Thread.sleep(10);
        } catch (Exception e) {

        }

    }
}
