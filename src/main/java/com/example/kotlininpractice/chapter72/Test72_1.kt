package com.example.kotlininpractice.chapter72

import com.example.kotlininpractice.extutil.mlog
import kotlinx.coroutines.*
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.EmptyCoroutineContext

fun main() = runBlocking {
    /*
    * 为什么能直接使用coroutineContext
    * */
    val job: Job? = coroutineContext[Job.Key]
    job?.children
    job?.mlog()
    val emptyCoroutineContext: ContinuationInterceptor? = EmptyCoroutineContext[ContinuationInterceptor]
    if (emptyCoroutineContext == null) "NULL".mlog()
    else emptyCoroutineContext.mlog()
    coroutineContext.isActive
}