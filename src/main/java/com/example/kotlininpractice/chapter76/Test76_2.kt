package com.example.kotlininpractice.chapter76

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import java.util.concurrent.Executors

val threadLocal = ThreadLocal<String>()

/*
* 想要讲的就是：使用asContextElement实现在协程中安全使用ThreadLocal，相当于每个协程也会像线程一样具有一个自己的ThreadLocal（即使此协程再多个线程之间来回切换）
* */
// 需要再验证下，，比如协程线程都创建一个ThreadLocal看会不会混乱
fun main() = runBlocking {
    threadLocal.set("hello")
    "before 初始值 = ${threadLocal.get()}".mlog()
    val job = launch(threadLocal.asContextElement("world")) {
        // 直接设置值貌似也行?
//         threadLocal.set("world--------")
        "new value = ${threadLocal.get()}".mlog()
        yield()
        "after change thread new value = ${threadLocal.get()}".mlog()
    }
    job.join()
    "after 初始值 = ${threadLocal.get()}".mlog()
    Unit
}

// 官方例子

