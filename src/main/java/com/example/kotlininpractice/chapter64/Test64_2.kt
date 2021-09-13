package com.example.kotlininpractice.chapter64

import com.example.kotlininpractice.extutil.delayNs
import kotlinx.coroutines.*
import sun.plugin.com.Dispatcher
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        "block".log()
        val time = measureTimeMillis {
            val deferredV1 = async { getIntValue1() }
            val deferredV2 = async { getIntValue2() }
            val v1 = deferredV1.await()
            val v11 = deferredV1.await()
            val v122 = deferredV1.await()
            val v1222 = deferredV1.await()
            val v2 = deferredV2.await()
            "v1 + v2 = ${v1 + v2}".log()
        }
        "耗时   $time".log()
    }
    "Main Thread".log()
}
private suspend fun getIntValue1(): Int {
    delayNs(1)
    "v1".log()
    return 2
}

private suspend fun getIntValue2(): Int {
    delayNs(2)
    "v2".log()
    return 3
}

private fun String.log() {
    println("$this   ${Thread.currentThread().name}")
}