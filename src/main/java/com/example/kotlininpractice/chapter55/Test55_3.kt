package com.example.kotlininpractice.chapter55

import com.example.kotlininpractice.extutil.log
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

fun main() {
    log("主线程 开始执行")
    val scope = CoroutineScope(EmptyCoroutineContext)
    scope.launch {
        log("自定义的")
    }
    Thread.sleep(1000)
    log("主线程 即将结束")
}