package com.example.kotlininpractice.chapter76

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main() = runBlocking {
    val executor = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    launch(executor) {
        val threadLocalA = object : ThreadLocal<Int>() {
            override fun initialValue(): Int {
                return 101.apply {
                    "初始值 = $this".mlog()
                }
            }
        }
        withContext(Dispatchers.IO) {
            "使用withContext切线程，设置为202".mlog()
            threadLocalA.set(202)
            "使用withContext切线程，设置结束，值 = ${threadLocalA.get()}".mlog()
        }
        delay(100)
        "获取原线程中的值 = ${threadLocalA.get()}".mlog()

        withContext(Dispatchers.IO) {
            "进入设置202的线程，获取值 = ${threadLocalA.get()}".mlog()
        }

        withContext(Executors.newCachedThreadPool().asCoroutineDispatcher()) {
            // 因为设置了默认值
            "进入没设置过值的线程，获取值 = ${threadLocalA.get()}".mlog()
        }
    }
    Unit
}