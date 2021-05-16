package com.example.kotlininpractice.chapter54

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val time = {
        for (i in 0 until 10) {
            launch {
                myDelay(1)
            }
        }
    }
    println(measureTimeMillis(time))
}

fun myDelay(i: Int = 1) {
    val time = System.currentTimeMillis()
    while (true) {
        if (System.currentTimeMillis() - time >= 1000 * i) return
    }
}

