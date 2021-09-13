package com.example.kotlininpractice.chapter59

import com.example.kotlininpractice.extutil.delayNs
import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

fun main() {
    GlobalScope.launch {

        coroutineScope {
            delay(3000)
            "coroutine scope".mlog()
        }

        repeat(100) {
            delay(500)
            "$it".mlog()
        }



    }
    TimeUnit.SECONDS.sleep(2)
}