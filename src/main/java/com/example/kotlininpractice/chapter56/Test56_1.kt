package com.example.kotlininpractice.chapter56

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    // 协程会等待自己的子协程运行结束后，自己才会结束执行
    launch {
        delay(3000)
        println("------------------")
    }
    GlobalScope.launch {
        delay(5000)
        println("注意：这里并不是runBlock的子协程")
    }
    delay(500)
    println("++++++++++++++++++++++")
}