package com.example.kotlininpractice.chapter66

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.lang.Exception
/*
* 关于父子协程的异常与取消问题
* 协程的取消总是沿着协程层次体系向上传播
* */
fun main() = runBlocking {
    try {
        failureComputation()
    } finally {
        "Computation failed".mlog()
    }
}

suspend fun failureComputation() {
    coroutineScope {
        val value1 = async {
            try {
                delay(90000000)
                50
            } finally {
                "value1 throw an exception".mlog()
            }
        }
        val value2 = async<Int> {
            Thread.sleep(2000)
            "value2 was throw exception"
            throw Exception()
        }
        value1.await() + value2.await()
    }
}