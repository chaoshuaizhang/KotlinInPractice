package com.example.kotlininpractice.chapter73

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val request = launch {

        GlobalScope.launch {
            "job1 : hello".mlog()
            delay(1000)
            "job1 : world".mlog()
        }

        launch {
            delay(100)
            "job2 : hello".mlog()
            delay(1000)
            "job2 : world".mlog()
        }
    }
    delay(500)
    request.cancel()
    delay(1000)
    "welcome".mlog()
}