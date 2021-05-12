package com.example.kotlininpractice.chapter61

import kotlinx.coroutines.*
import java.lang.Exception

// -Dkotlinx.coroutines.debug
fun main() = runBlocking {
    // 视频里边用的是GlobalScope.launch，感觉在当前场景中用得不对，globalscope会新开一个线程，launch(Diapatchers.XXX也会新起一个线程，这就没有问题)
    val job = launch {
        val l = System.currentTimeMillis()
        try {
            repeat(200) {
                println("hello  $it")
                delay(500)
            }
        } catch (e: Exception) {
            // 这里的时间大致是1100毫秒，也就是说，程序的结束是在delay进行的过程中，而不是在delay结束后的检查过程中
            println(System.currentTimeMillis() - l)
            e.printStackTrace()
        }
    }
    delay(1100)
    println("hello world")
    try {
        job.cancel(CancellationException("CANCEL JOB ..."))
    } catch (e: Exception) {
        // 这里并不会catch到CancellationException，那必须不会！！异常又不是在这里抛出的，是在上面的repeat代码块里，准确的说是在delay方法里
        e.printStackTrace()
    }
    job.cancel()
    // 测试时发现，在当前场景下不加join也行，isActive isCancelled isCompleted这几个状态加不加都一致
    job.join()
    job.cancelAndJoin()
    println("welcome")
}

//fun main() = runBlocking {
//    val job: Job = GlobalScope.launch {
//        repeat(1000) {
//            println("TAG   --->   $it")
//            delay(500)
//        }
//    }
//    println("Hello Kotlin")
//    delay(1100)
//    job.cancel()
//    delay(3000)
//}

// withTimeout

//// 这里讲的就是 可以把withTimeout的代码放到try-catch中
//fun main() = runBlocking {
//    try {
//        withTimeout(1000) {
//            println("-------")
//            delay(1100)
//            println("+++++++")
//        }
//    } catch (e: Exception) {
//        println("超时异常：${e.message}")
//    }
//}

//suspend fun intValye(): Int {
//    delay(2000)
//    return 15
//}
//
//suspend fun intValue2(): Int {
//    delay(3000)
//    return 20
//}
//
//fun main() = runBlocking {
//    GlobalScope.launch {  }
//    val time = measureTimeMillis {
//        println((intValye() + intValue2()))
//    }
//    println("执行时间   $time")
//}

// 把上述的串行修改为并行，使用async和await实现并发