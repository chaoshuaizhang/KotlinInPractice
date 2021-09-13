package com.example.kotlininpractice.daily.inaction.测试javakotlin注册接口的区别;

public class MyJavaClass {
    void addJListener(MyJavaListener listener) { }
    void addKListener(MyKotlinListener listener) { }

    public static void main(String[] args) {
        MyJavaClass myJavaClass = new MyJavaClass();
        myJavaClass.addJListener(() -> {

        });
        // kotlin类添加java接口
        MyKotlinClass kotlinClass = new MyKotlinClass();
        // 这里直接变成了lambda
        kotlinClass.addJListener(() -> { });
    }
}
