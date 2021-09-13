package com.example.kotlininpractice.chapter57

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

/*
* 对比线程和协程的效率
* */
// 循环开启协程
//fun main() = runBlocking {
//    repeat(100) {
//        launch {
//            delay(1000)
//            println("A - TAG")
//        }
//    }
//    println("Hello World")
//}

// 循环开启线程
//fun main() {
    // 为什么不崩呢？难道是打开线程池的方式不对？
//    val executor = Executors.newFixedThreadPool(Int.MAX_VALUE)
//    repeat(100000) {
//        executor.execute {
//            Thread.sleep(1000)
//            println("B -> TAG")
//        }
//    }
//    println("Hello World")
//}
