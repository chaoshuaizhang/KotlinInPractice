package com.example.kotlininpractice.daily.inaction.委托属性

import com.example.kotlininpractice.daily.inaction.委托属性.反编译.OrderDTO
import com.example.kotlininpractice.daily.inaction.运算符重载.WordDTO

val dto by lazy {
    WordDTO().apply {
        num = 100
    }
}

val order by lazy(LazyThreadSafetyMode.NONE) {
    OrderDTO()
}