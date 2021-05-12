package com.example.kotlininpractice.chapter62

import kotlinx.coroutines.*

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        println(Thread.currentThread().name)
        var nextPrintTime = startTime
        var i = 0
        while (i < 20) {
            if (System.currentTimeMillis() > nextPrintTime) {
                println("job:   ${i++}")
                nextPrintTime += 500
            }
        }
    }
    println("hello")
    delay(1300)
    yield()
    job.cancel()
    println("welcome")
}