package com.example.kotlininpractice.chapter63

import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

// TODO 看下Android Studio里边的异常会不会有堆栈信息
fun main() = runBlocking {
    withTimeout(10) {
        repeat(10) {
            println("----- $it -----")
//            delay(100)
            while (isActive){
                println("-----------")
            }
        }
    }
    delay(600)
    println("==========================")
}