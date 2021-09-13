package com.example.kotlininpractice.daily.inaction.Unit_Void_Nothing类型;

import java.util.concurrent.Callable;

public class TestJava {
    public static void main(String[] args) {
addCallback(() -> {
    return null;
});
    }

    private static void addCallback(Callable<Void> cb) { }

}
