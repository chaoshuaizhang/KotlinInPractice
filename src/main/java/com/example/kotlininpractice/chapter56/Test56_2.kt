package com.example.kotlininpractice.chapter56

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main() {
    // 协程会等待自己的子协程运行结束后，自己才会结束执行
    "".mlog()
    val parent = GlobalScope.launch {
        val sun = GlobalScope.launch {
            delay(3000)
            "----------------".mlog()
        }
        delay(1500)
        "++++++++++++++++++++++".mlog()
    }
    TimeUnit.SECONDS.sleep(1)
    "此时外部的协程已经取消了，但是内部的sun协程还会继续运行${parent.isActive}".mlog()
    TimeUnit.SECONDS.sleep(5)
}