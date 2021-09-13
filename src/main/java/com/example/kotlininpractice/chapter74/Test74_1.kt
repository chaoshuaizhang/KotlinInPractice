package com.example.kotlininpractice.chapter74

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val request = launch {
        repeat(5) {
            launch {
                delay((it + 1) * 100L)
                "Coroutine $it 执行完毕".mlog()
            }
        }
        "hello".mlog()
    }
     request.join()
    "world".mlog()
}