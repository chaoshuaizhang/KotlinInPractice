package com.example.kotlininpractice.chapter74

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*

fun main() = runBlocking(CoroutineName("Block-Coroutine")) {

    val v1 = async(CoroutineName("Async-1")) {
        mlog()
        10
    }
    val v2 = async(CoroutineName("Async-2")) {
        mlog()
        5
    }

    "${v1.await() * v2.await()}".mlog()

}