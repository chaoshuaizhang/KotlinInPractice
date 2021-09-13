package com.example.kotlininpractice.chapter57

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import kotlin.concurrent.thread

// 测试runBlocking
// 这里可以看出runblocking会阻塞线程
//fun main() = runBlocking {
//    launch {
//        delay(1000)
//        "Kotlin".mlog()
//    }
//    // 这里可以看出runblocking会阻塞协程
//    runBlocking {
//        delay(500)
//        "block2".mlog()
//    }
//    "hello".mlog()
//}

// 测试GlobalScope
//fun main() = runBlocking {
//    // globalscope不阻塞协程，也不阻塞线程
//    GlobalScope.launch {
//        launch {
//            delay(1000)
//            "Kotlin".mlog()
//        }
//        "hello".mlog()
//    }
//    "mainAAA".mlog()
//    delay(2000)
//    "mainBBB".mlog()
//}

// 测试coroutineScope
fun main() = runBlocking {
    "block".mlog()
    // coroutineScope阻塞当前协程
    coroutineScope {
        "hello".mlog()
        delay(1000)
        "world".mlog()
    }
    "Bye".mlog()
}