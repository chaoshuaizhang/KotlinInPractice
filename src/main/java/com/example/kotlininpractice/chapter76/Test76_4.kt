package com.example.kotlininpractice.chapter76

import kotlinx.coroutines.*
import java.util.concurrent.Executors

fun main()= runBlocking {
    val myThreadLocal = ThreadLocal<String?>()
    println(myThreadLocal.get()) // Prints "null"
    launch(Dispatchers.Default + myThreadLocal.asContextElement(value = "foo")) {
        println(myThreadLocal.get()) // Prints "foo"
        GlobalScope.launch(Executors.newCachedThreadPool().asCoroutineDispatcher()) {
            println(myThreadLocal.get()) // Prints "foo", but it's on UI thread
        }
    }
    println(myThreadLocal.get()) // Prints "null"
}