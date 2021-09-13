package com.example.kotlininpractice.daily.inaction.运算符重载;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class WordDTO implements Comparable<WordDTO>, Comparator<WordDTO> {

    public int num = 0;

// java里自定义的 乘操作
public int cheng(WordDTO other) {
    return num * other.num;
}

    @Override
    public int compareTo(@NotNull WordDTO o) {
        return 0;
    }

    @Override
    public int compare(WordDTO o1, WordDTO o2) {
        return 0;
    }
}
