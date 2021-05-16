package com.example.kotlininpractice.chapter66

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() {
    myLog()
    println("----- 开始执行 -----")
    // getIntValue1Async现在是普通函数，所以不需要在挂起函数中调用
    val v1 = getIntValue1Async()
    val v2 = getIntValue2Async()
    // 因为runBlock是阻塞当前线程的
    GlobalScope.launch(Dispatchers.Default) {
        // await是挂起函数，所以需要在协程或者另一个挂起函数中使用
        myLog()
        println("v1 + v2 = ${v1.await() + v2.await()}")
    }
    println("----- 开始暂停 -----")
    Thread.sleep(10_000)
    println("----- 执行结束 -----")
}

private suspend fun getIntValue1(): Int {
    delay(1000)
    return 1
}

private suspend fun getIntValue2(): Int {
    delay(1500)
    return 2
}

private fun getIntValue1Async(): Deferred<Int> {
    // 返回的是一个协程，所以方法不需要加suspend
    return GlobalScope.async {
        myLog()
        getIntValue1()
    }
}

private fun getIntValue2Async(): Deferred<Int> {
    return GlobalScope.async {
        myLog()
        getIntValue2()
    }
}

private fun myLog() {
    println(Thread.currentThread().name)
}