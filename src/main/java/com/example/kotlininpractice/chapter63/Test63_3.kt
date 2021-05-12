package com.example.kotlininpractice.chapter63

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main() = runBlocking {
    val res: Unit? = withTimeoutOrNull(500) {
        repeat(1) {
            println("----- $it -----")
            delay(100)
        }
    }
    delay(600)
    if (res == null) {
        println("程序超时")
    } else {
        println("result = $res")
    }
}