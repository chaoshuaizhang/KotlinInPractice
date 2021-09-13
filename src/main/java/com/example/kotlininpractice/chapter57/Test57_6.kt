package com.example.kotlininpractice.chapter57

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/*
* 注意：下面所有的操作都执行在 main 线程之中
* */
@ExperimentalStdlibApi
fun main() = runBlocking {
    "${coroutineContext[CoroutineDispatcher]}".mlog()
    launch {
        delay(1000)
        "my job1".mlog()
    }
    "hello".mlog()
    // 既然coroutineScope不会阻塞主线程，那么并且它是挂起函数，为什么[Bye]是在最后输出？
    // 这是因为runBlock的原因.......不太确定
    coroutineScope {
        launch {
            delay(5000)
            "my job2".mlog()
            "${coroutineContext[CoroutineDispatcher]}".mlog()
        }
        delay(3000)
        "world".mlog()
    }
    "Bye".mlog()
}


//fun main() {
//    GlobalScope.launch {
//        launch {
//            delay(1000)
//            "my job1".mlog()
//        }
//        "hello".mlog()
//        // 既然coroutineScope不会阻塞主线程，那么并且它是挂起函数，为什么[Bye]是在最后输出？
//        // 这是因为runBlock的原因
//        coroutineScope {
//            launch {
//                delay(5000)
//                "my job2".mlog()
//            }
//            delay(3000)
//            "world".mlog()
//        }
//        "Bye".mlog()
//    }
//    "main".mlog()
//    TimeUnit.SECONDS.sleep(10)
//}