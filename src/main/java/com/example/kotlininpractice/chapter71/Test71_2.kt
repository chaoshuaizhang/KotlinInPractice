package com.example.kotlininpractice.chapter71

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*

fun main() = runBlocking {
    val job1 = launch(Dispatchers.IO) {
        val job = coroutineContext[Job]
        job?.mlog()
    }
    job1.mlog()
}