package com.example.kotlininpractice.chapter76

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.Executors

fun main() = runBlocking(Dispatchers.IO) {
    val myThreadLocal = ThreadLocal<String>()
    myThreadLocal.set("main")
    withContext(Dispatchers.IO) {
        myThreadLocal.get().mlog() // Prints "main"
        myThreadLocal.set("UI")
    }
    val list = mutableListOf<String>()
    list.stream().noneMatch {
        true
    }
    val l: List<String> = list.filter {
        true
    }
    myThreadLocal.get().mlog() // Prints "main", not "UI"
}