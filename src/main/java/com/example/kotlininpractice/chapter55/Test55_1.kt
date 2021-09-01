package com.example.kotlininpractice.chapter55

import com.example.kotlininpractice.extutil.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    log("当前线程")
    launch {
        log("不指定调度器，则运行在当前线程")
    }
    Unit
}