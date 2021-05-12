package com.example.kotlininpractice.chapter2

import java.awt.Button
import java.util.function.Consumer

fun main() {
    val list = listOf("A", "S", "D", "F", "G")
    // 多种方式遍历list集合

    list.forEach {
        print("$it  ")
    }

    // until是 [ )
    for (i in 0 until list.size) {
        print("${list[i]}  ")
    }

    // 注意 .. 是[]，所以遍历集合时会数组越界，所以需要 -1
    for (i in 0..list.size - 1) {
        print("${list[i]}  ")
    }

    // 注意，这个我基本上没用过，这个是很方便的，在一些场景下可以代替 until ..
    for (e in list) {
        print("$e  ")
    }

    // 使用类似java8里边的函数式编程的方式
    list.forEach(object : Consumer<String> {
        override fun accept(t: String) {
            println(t)
        }
    })

    list.forEach(Consumer {
        println(it)
    })

    // 通过方法引用的方式实现
    list.forEach(System.out::println)

}