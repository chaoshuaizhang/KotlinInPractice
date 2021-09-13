package com.example.kotlininpractice.chapter73

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*

// 父子协程关系
/*
* 在GlobalScope中创建的子协程，会随着GlobalScope的取消而取消
* */
fun main() {
    runBlocking {
        GlobalScope.launch {
            launch {
                try {
                    "____${coroutineContext[Job]}".mlog()
                    "子协程开始等待".mlog()
                    delay(3_000)
                    "子协程等待结束".mlog()
                } finally {
                    "子协程执行结束".mlog()
                }
            }
            delay(1000)
            "取消子协程".mlog()
            coroutineContext.mlog()
            coroutineContext.cancel()
        }
        Unit
    }

    Thread.sleep(15000)
}