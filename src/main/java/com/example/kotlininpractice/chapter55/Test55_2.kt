package com.example.kotlininpractice.chapter55

import com.example.kotlininpractice.extutil.log
import kotlinx.coroutines.*

fun main() {
    log("主线程 开始执行")
    GlobalScope.launch {
        log("不指定调度器，则运行在当前线程，但当前线程不是 main 线程")
    }
    Thread.sleep(1000)
    log("主线程 即将结束")
}