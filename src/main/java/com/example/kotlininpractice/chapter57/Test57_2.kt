package com.example.kotlininpractice.chapter57

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main() = runBlocking {
    val job = GlobalScope.launch {
        delay(1000)
        "Kotlin Coroutines".mlog()
    }
    "Hello".mlog()
    // 使用join，让当前协程等待其他协程执行结束后再开始执行
    job.join()
    "Bye".mlog()
}