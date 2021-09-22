package com.example.kotlininpractice.daily.分享;

public class JavaTest {
    public static void main(String[] args) {
        Word word = new Word();
        word.getClass().getDeclaredMethods()[0].setAccessible(true);
    }
}
