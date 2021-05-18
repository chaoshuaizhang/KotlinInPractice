package com.example.kotlininpractice.chapter54

import com.example.kotlininpractice.extutil.delayNs
import com.example.kotlininpractice.extutil.log
import kotlinx.coroutines.*

/*
* 主要看下新起的协程运行在哪个线程
* 下面的代码运行在协程上面，而协程又运行在线程上面，
* 会发现一个问题，delay之前两个协程运行在不同的线程之上，但
* 是delay结束后，两个协程所在的线程有可能发生了变化，但是协程没有变。
* 甚至主协程所在的线程也发生了变化，难道是线程复用原因？线程没事儿干
* 的时候让他挂掉，等协程重新变为运行的时候，哪个线程空闲，就把它挂到哪个线程上？
* */
fun main() {
    GlobalScope.launch {
        log("主协程开始执行")
        val deferred1 = GlobalScope.async(Dispatchers.IO) {
            log("GlobalScope async  1")
            delay(4000)
            log("协程 1 结束延时")
            "123"
        }
        val deferred2 = GlobalScope.async(Dispatchers.IO) {
            log("GlobalScope async  2")
            delay(3000)
            log("协程 2 结束延时")
            "345"
        }
        println(deferred1.await() + deferred2.await())
        log("GlobalScope launch")
    }

    Thread.sleep(1000)
    log("主线程即将进入睡眠状态")

    Thread.sleep(10_000)
}