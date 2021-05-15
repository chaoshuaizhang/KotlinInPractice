package com.example.kotlininpractice.chapter03;

public class Test3_2 {
    public static void main(String[] args) {
        System.out.println("这是 java 文件");
        Test3_3Kt.main2();
        // new的时候不会报错，但是会有警告，编译时会报错
//        Test3_3Kt a = new Test3_3Kt();
//        System.out.println(a.hashCode());
    }
}
