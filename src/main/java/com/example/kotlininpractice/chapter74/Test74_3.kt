package com.example.kotlininpractice.chapter74

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*

fun main() = runBlocking {
    // The elements from this context with the same key as in the other one are dropped.
    launch(Dispatchers.IO + CoroutineName("My-Launcher") + CoroutineName("My-Launcher-2")) {
        "".mlog()
    }
    Unit
}