package com.example.kotlininpractice

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.Exception

private fun myMethod(): Flow<Int> = flow { // 流构建器
//    println("flowThread: ${Thread.currentThread().name}")
    for (i in 1..4) {
        delay(100) // 不能使用sleep方法，否则不会异步执行
        // 发送下一个值
        println("============        ${Thread.currentThread().name}")
        emit(i)
        throw IllegalAccessException()
    }
}.flowOn(Dispatchers.IO)

@InternalCoroutinesApi
fun main() = runBlocking<Unit> {

    try {
        throw AssertionError("sssssss")
    }catch (t:Exception){
        println(t.message)
        t.printStackTrace()
    }

    val q1: String? = "1"
    val q2: String? = null
    val q3: String = "3"
    println(q1 ?: q2 ?: q3)
    println("=====================================")

    val str1 = "="
    var split: List<String> = str1.split("==")
    println(split)
    var s1 = split[0]
    println(s1)


    val list = mutableListOf<Any>("11", "22", "33")
    println(list)
    // 启动并发的协程以验证主线程并未阻塞
    launch {
        println(Thread.currentThread().name)
        for (i in 1..4) {
            println("hello: $i")
            delay(1000)
        }
    }
    // 收集这个流
    myMethod()
            .catchStr {
                it
            }
            .collect(object : FlowCollector<Int> {
                override suspend fun emit(value: Int) {
                    println("${Thread.currentThread().name}" + value)
                }
            })

    var ss = ""
    addListener { s, f ->

    }

}

public fun <T> Flow<T>.catchStr(action: suspend FlowCollector<T>.(cause: String) -> Unit): Flow<T> =
        flow {
            action("exception")
        }

fun addListener(cb: String.(s: Int, f: Float) -> Unit) {
    val s = ""
    s.cb(1, 1f)
}