package com.example.kotlininpractice.daily.inaction.委托属性

import com.example.kotlininpractice.daily.inaction.运算符重载.WordDTO

val dto by lazy {
    WordDTO().apply {
        num = 100
    }
}