package com.example.kotlininpractice.chapter64

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.math.log
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    "block".myLog()
    println("耗时 ： ${
    measureTimeMillis {
        // 下面的两个函数之间不存在依赖关系，但还是串行执行的
        // 所以如果设置成并行执行，那么执行时间会小于串行执行的时间
        val v1 = getIntValue1()
        val v2 = getIntValue2()
        println("v1 + v2 = ${v1 + v2}")
    }
    }")
}

private suspend fun getIntValue1(): Int {
    delay(1000)
    "v1".myLog()
    return 2
}

private suspend fun getIntValue2(): Int {
    delay(1500)
    "v2".myLog()
    return 3
}

private fun String.myLog() {
    println("$this   ${Thread.currentThread().name}")
}