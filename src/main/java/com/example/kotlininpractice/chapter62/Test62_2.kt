package com.example.kotlininpractice.chapter62

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        println(measureTimeMillis {
            while (isActive && i < 20) {
                if (System.currentTimeMillis() > nextPrintTime) {
                    println("job:   ${i++}")
                    nextPrintTime += 500
                }
            }
        })
    }
    println("hello")
    delay(1300)
    job.cancelAndJoin()
    println("welcome")
}