package com.example.kotlininpractice.chapter66

import kotlinx.coroutines.*

fun main() {
    println("----- 开始执行 -----")
    // getIntValue1Async现在是普通函数，所以不需要在挂起函数中调用
    val value1 = getIntValue1Async()
    val value2 = getIntValue2Async()
    // 注意，假设刚调用完异步函数，其他的业务代码出错了，此时协程是无法停下来的
    try {
        Thread.sleep(100)
        throw Exception("error happen")
    }  finally {
        println("isActive: ${value1.isActive}, isCancelled: ${value1.isCancelled}, isCompleted: ${value1.isCompleted}")
        println("isActive: ${value2.isActive}, isCancelled: ${value2.isCancelled}, isCompleted: ${value2.isCompleted}")
    }
    // 因为runBlock是阻塞当前线程的
    // await是挂起函数，所以需要在协程或者另一个挂起函数中使用
//    println("v1 + v2 = ${v1.await() + v2.await()}")
    println("----- 执行结束 -----")
}

private suspend fun getIntValue1(): Int {
    delay(1000)
    return 1
}

private suspend fun getIntValue2(): Int {
    delay(3500)
    return 2
}

private fun getIntValue1Async(): Deferred<Int> {
    // 返回的是一个协程，所以方法不需要加suspend
    return GlobalScope.async {
        while (true) {
            println("==============================")
        }
        getIntValue1()
    }
}

private fun getIntValue2Async(): Deferred<Int> {
    return GlobalScope.async {
        while (true) {
            println("++++++++++++++++++++++++++++++")
        }
        getIntValue2()
    }
}