package com.example.kotlininpractice.daily.分享.接口相关.默认方法;

import com.example.test.分享.接口相关.默认方法.ITest;import org.jetbrains.annotations.NotNull;

public class TestJava implements ITest {

    @Override
    public void defFun(@NotNull String tag) {
        DefaultImpls.defFun(this, tag);
    }
}