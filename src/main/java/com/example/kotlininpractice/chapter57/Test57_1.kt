package com.example.kotlininpractice.chapter57

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    GlobalScope.launch {
        delay(1000)
        "Kotlin Coroutines".mlog()
    }
    "Hello".mlog()
    // 延时2s，等待上边global launch结束后，当前协程才结束
    // 那么有没有什么办法不用延时的方式，实现当前协程等待global launch完成后再结束呢？
    // 有，使用join
    delay(2000)
    "Bye".mlog()
}