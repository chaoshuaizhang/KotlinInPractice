package com.example.kotlininpractice.chapter62

import kotlinx.coroutines.*

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 20) {
            if (System.currentTimeMillis() > nextPrintTime) {
                println("job:   ${i++}")
                nextPrintTime += 500
            }
            mySuspendFunction(this)
        }
    }
    println("hello")
    delay(1300)
    job.cancelAndJoin()
    println("welcome")
}

suspend fun mySuspendFunction(coroutineScope: CoroutineScope){
    // delay()
    if(!coroutineScope.isActive)
        throw CancellationException("Custom cancel ...")
}