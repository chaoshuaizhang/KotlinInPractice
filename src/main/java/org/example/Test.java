package org.example;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

/*
 * @author zcs
 * */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true && !Thread.currentThread().isInterrupted()) ;
            System.out.println(111);
        });
        thread.setDaemon(false);
        thread.start();
        Thread.sleep(2_000);
        thread.interrupt();
//        int num = 128;
//        int count = 0;
//        if (num < 1 << 7) {
//            count = 1;
//        } else if (num < (1 << 15)) {
//            count = 2;
//        } else if (num < 2 << 23) {
//            count = 3;
//        } else if (num < 1 << 31 - 1) {
//            count = 4;
//        } else {
//            throw new IllegalArgumentException("Too large ...");
//        }
//        System.out.println("count = " + count);
//        byte[] bytes = intToByteArray(count, num);
//        System.out.println(byteArrayToInt(count, bytes));
    }

    public static byte[] intToByteArray(int count, int i) {
        byte[] result = new byte[count];
        for (int j = 0; j < count; j++) {
            result[j] = (byte) ((i >> (8 * (count - 1 - j))) & 0xFF);
        }
        return result;
    }

    public static int byteArrayToInt(int count, byte[] bytes) {
        int value = 0;
        for (int i = 0; i < count; i++) {
            int shift = (count - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;
        }
        return value;
    }

}
