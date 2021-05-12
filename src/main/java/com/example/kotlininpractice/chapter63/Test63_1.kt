package com.example.kotlininpractice.chapter63

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        val l = System.currentTimeMillis()
        try {
            withContext(NonCancellable) {
                repeat(20) {
                    println("Job:   $it")
                    delay(500)
                }
            }
        } finally {
            println("${System.currentTimeMillis() - l}")
            withContext(NonCancellable) {
                println("do finally ...    ${Thread.currentThread().name}   $isActive")
                delay(1)
                println("do finally over ...")
            }
        }
    }
    println("hello")
    delay(1300)
    job.cancelAndJoin()
    println("welcome")
}