package com.example.kotlininpractice.chapter64

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = measureTimeMillis {
        val res1 = async(context = Dispatchers.IO,
                start = CoroutineStart.LAZY) {
            delay(1000)
            2
        }
        val res2 = async(
                context = Dispatchers.IO,
                start = CoroutineStart.LAZY) {
            delay(3000)
            3
        }
        println("-----------开始执行-----------")
        // TODO 这块儿需要注意，调不调用start方法影响着下面的执行时间
//        res1.start()
//        res2.start()
        // 在不调用start的前提下，当执行res1.await时，res2是没有开始执行
        // 的，直到res1执行结束，才会执行到下一行代码，才会执行res2的await
        val v1 = res1.await()
        println("----------------------")
        val v2 = res2.await()
        println(v1 + v2)
    }
    println(time)
}