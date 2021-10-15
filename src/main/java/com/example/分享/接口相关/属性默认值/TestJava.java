package com.example.分享.接口相关.属性默认值;

import com.example.test.分享.接口相关.属性默认值.ITest;
import org.jetbrains.annotations.NotNull;

public class TestJava implements ITest {
    @NotNull
    @Override
    public String getTag() {
        return null;
    }

    @Override
    public void setTag(@NotNull String tag) {

    }

    @NotNull
    @Override
    public String get_tag() {
        return null;
    }
}
