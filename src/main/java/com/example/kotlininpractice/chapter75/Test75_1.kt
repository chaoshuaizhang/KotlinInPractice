package com.example.kotlininpractice.chapter75

import com.example.kotlininpractice.extutil.delayNs
import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import java.lang.Exception

/*
* 只需要关注myScope就可以
* */
class Activity(private val myScope: CoroutineScope) : CoroutineScope by myScope {
    fun onDestroy() {
        myScope.cancel()
    }

    fun doSth() {
        repeat(8) {
            myScope.launch {
                try {
                    withContext(Dispatchers.IO) {
                        // IO
                        delayNs((it + 1))
                    }
                    // UI
                    "Coroutine $it is 完成".mlog()
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    "Coroutine $it is 执行 Finally".mlog()
                }
            }
        }
    }
}

fun main() = runBlocking {
    // 由我们传入一个父协程
    val activity = Activity(CoroutineScope(Dispatchers.IO))
    "启动协程".mlog()
    activity.doSth()
    delay(3250)
    "Activity消毁".mlog()
    activity.onDestroy()
    delay(10_000)
}