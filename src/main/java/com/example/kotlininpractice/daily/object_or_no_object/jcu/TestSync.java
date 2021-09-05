package com.example.kotlininpractice.daily.object_or_no_object.jcu;

public class TestSync {

    private static final TestSync INSTANCE = new TestSync();

    public static void main(String[] args) {
    }

    private void funSync() {
        synchronized (this) {
            int i = 9;
            {
                int ik = 0;
                {
                    ik++;
                }
            }
            System.out.println(i);
        }
    }

    private static synchronized void classSync() {
        synchronized (INSTANCE) {
            int i = 9;
            System.out.println(i);
        }
    }

}
