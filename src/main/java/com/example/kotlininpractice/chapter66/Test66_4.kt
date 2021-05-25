package com.example.kotlininpractice.chapter66

import kotlinx.coroutines.*

fun main() = runBlocking {
    GlobalScope.launch {
        println("11111")
        withContext(Dispatchers.IO) {
            delay(3000)
        }
        println("---------------1")
    }

    GlobalScope.launch {
        println("22222")
        withContext(Dispatchers.IO) {
            delay(3000)
        }
        println("---------------2")
    }
    delay(10000)
}