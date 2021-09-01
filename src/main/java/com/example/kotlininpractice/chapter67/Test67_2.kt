package com.example.kotlininpractice.chapter67

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*

/*
* Dispatchers.Unconfined
* */
fun main() = runBlocking {
    launch(Dispatchers.Unconfined) {
        "Unconfined Dispatcher".mlog()
        // 上一个代码也说了，这里执行完delay后，会进行线程切换
        delay(1)
        // mySuspendFunction()
        "Unconfined Dispatcher".mlog()
    }

    launch {
        "No Params Dispatcher".mlog()
        delay(1)
        "No Params Dispatcher".mlog()
    }

    Unit
}

suspend fun mySuspendFunction() {
    "my suspend function".mlog()
    GlobalScope.launch { }
}