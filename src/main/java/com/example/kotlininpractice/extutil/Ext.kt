package com.example.kotlininpractice.extutil

import java.time.Instant
import java.time.LocalDateTime
import java.util.*

/*
* CPU空转 n 秒
* */
fun delayNs(n: Int) {
    val time = System.currentTimeMillis()
    while (true) {
        if (System.currentTimeMillis() - time > n * 1000) return
    }
}

fun log(msg: String = "") {
    println("${LocalDateTime.now()} : $msg  ${Thread.currentThread().name}")
}

fun Any.mlog() {
    println("$this   ${Thread.currentThread().threadGroup.name}-${Thread.currentThread().name}")
}