package com.example.kotlininpractice.chapter54

import com.example.kotlininpractice.extutil.log
import kotlinx.coroutines.*

fun main() {
    Job
    val coroutine = GlobalScope.launch(NonCancellable + Dispatchers.Default) {
        delay(1000)
        log("Kotlin Coroutine")
    }
    log("Hello")
    Thread.sleep(2000)
    log("Finish")
    try {
        throw Exception()
    } finally {
        log("isActive=${coroutine.isActive}  isCancel=${coroutine.isCancelled}")
    }
}