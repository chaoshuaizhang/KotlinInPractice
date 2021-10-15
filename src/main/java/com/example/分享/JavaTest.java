package com.example.分享;

import com.example.test.分享.Word;

public class JavaTest {
    public static void main(String[] args) {
        Word word = new Word();
        word.getClass().getDeclaredMethods()[0].setAccessible(true);
    }
}
