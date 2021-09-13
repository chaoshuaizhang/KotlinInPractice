package com.example.kotlininpractice.daily.inaction.运算符重载;

import java.util.List;

public class TestJava {
    public static void main(String[] args) {
        WordDTO wordDTO = new WordDTO();
        // 加 操作
        List<WordDTO> wordDTOS = TestKt.plus(wordDTO, new WordDTO());
        // 乘 操作
        wordDTO.cheng(new WordDTO());
    }
}
