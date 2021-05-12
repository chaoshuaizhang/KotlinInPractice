package com.example.kotlininpractice.chapter62

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(20) {
                println("Job:   $it")
                delay(500)
            }
        } finally {
            println("do finally ...    ${Thread.currentThread().name}   $isActive")
            delay(10)
            println("do finally over ...")
        }
    }
    println("hello")
    delay(1300)
    job.cancelAndJoin()
    println("welcome")
}