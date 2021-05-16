package com.example.kotlininpractice.chapter64

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val res = async(start = CoroutineStart.ATOMIC) {
        println("---------- 我开始执行了")
        delay(3000)
        println("---------- 我执行结束了")
    }
    println("取消执行     ${res.cancel()}")
    println("++++++++++")
    delay(2000)
    delay(2000)
    println("========== 开始执行await方法")
    res.await()
}