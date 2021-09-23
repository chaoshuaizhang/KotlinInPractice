package com.example.kotlininpractice.daily.inaction.内联

import kotlin.concurrent.thread
import kotlin.random.Random

fun main() {
    // request()
    response()
}

private fun request() {
    val result: () -> Unit = testInline({
        println(it)
    }, {
        println("too")
    })
    result.invoke()
}
private fun response()   {
    addListener {
        println("button click")
        println("return result")
        return@addListener
    }
    println("finish")
}

inline fun addListener(crossinline listener: () -> Unit) {
    thread {
        listener.invoke()
    }
}

inline fun testInline(first: (String) -> Unit, noinline second: () -> Unit): () -> Unit {
    first.invoke("Nice to meet you")
    println("emmmmmmm")
    second.invoke()
    return second
}