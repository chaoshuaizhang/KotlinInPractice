package com.example.kotlininpractice.chapter71

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*

fun main() {
    newSingleThreadContext("context1").use { ctx1 ->
        newSingleThreadContext("context2").use { ctx2 ->
            runBlocking(ctx1) {
                // 注意下面打印的三句话都是运行在同一个协程的
                // 首先 start和back肯定是同一协程，withContext又不会创建新的协程，它是
                // 把当前协程拿到别的线程中执行，所以这三个都是同一个协程
                "Start in Context1".mlog()
                withContext(ctx2){
                    // 这里的协程应该是归属于ctx1的，视频里讲的应该有问题，说归属于ctx2
                    "Working in Context2".mlog()
                }
                "Back to Context1".mlog()
            }
        }
    }
}