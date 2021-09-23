package com.example.kotlininpractice.daily.分享.扩展方法

import com.example.kotlininpractice.daily.分享.Word

fun Word.print() {
    println("$word : $trans")
}

fun main() {
    Word().apply { print() }
}