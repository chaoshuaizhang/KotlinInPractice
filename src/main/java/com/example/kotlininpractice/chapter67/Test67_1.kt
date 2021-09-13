package com.example.kotlininpractice.chapter67

import com.example.kotlininpractice.extutil.delayNs
import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import java.util.concurrent.Executors

@ExperimentalStdlibApi
fun main() = runBlocking {

    "Parent runBlocking Dispatcher : ${coroutineContext[CoroutineDispatcher]}".mlog()
    launch {
        // 默认不写任何分发器，输出的竟然不是Default
        "No Dispatcher params : ${coroutineContext[CoroutineDispatcher]}".mlog()
    }
    launch(Dispatchers.Unconfined) {
        "Unconfined Dispatcher Before delay : ${coroutineContext[CoroutineDispatcher]}".mlog()
        // 加上delay函数后，上边和下边打印的线程不一样了，由main切换到了DefaultExecutor
        delay(1)
        // 加上自定义的delay函数，则没有任何影响
        // delayNs(1)
        "Unconfined Dispatcher After delay : ${coroutineContext[CoroutineDispatcher]}".mlog()
    }
    launch(Dispatchers.IO) {
        "Io Dispatcher : ${coroutineContext[CoroutineDispatcher]}".mlog()
    }
    launch(Dispatchers.Default) {
        "Default Dispatcher : ${coroutineContext[CoroutineDispatcher]}".mlog()
    }
    GlobalScope.launch {
        "GlobalScope No Dispatcher : ${coroutineContext[CoroutineDispatcher]}".mlog()
    }
    val coroutine = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    launch(coroutine) {
        "Custom Single Thread pool : ${coroutineContext[CoroutineDispatcher]}".mlog()
        coroutine.close()
    }
    Unit
}
/*
*
* */