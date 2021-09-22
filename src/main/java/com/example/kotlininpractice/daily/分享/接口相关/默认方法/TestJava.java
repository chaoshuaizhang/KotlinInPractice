package com.example.kotlininpractice.daily.分享.接口相关.默认方法;

public class TestJava implements ITest {

    public static void main(String[] args) {
        TestJava testJava = new TestJava();
        testJava.defFun();
    }

    @Override
    public void defFun() {
        ITest.DefaultImpls.defFun(this);
    }
}
