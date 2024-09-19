package testkotlin.coroutine

import kotlinx.coroutines.*
import org.jetbrains.annotations.TestOnly
import java.time.LocalDateTime

private lateinit var scope: CoroutineScope

fun main() = cancel6()

fun main1() = runBlocking {
    GlobalScope.launch {
        try {
            delay(10_000)
        } catch (e: Exception) {
            log(e.message)
        }
        log("1")
    }
    launch {
        delay(3_000)
        log("4")
    }
    coroutineScope {
        delay(500)
        log(2)
    }
    coroutineScope {
        delay(6000)
        log(3)
    }
}

fun main2() = runBlocking {
    // 结构化并发，runBlocking会等scope执行结束后再停止
    // scope = CoroutineScope(coroutineContext + Dispatchers.IO)
    // 非结构化并发，runBlocking结束后scope就无法继续执行了
    scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    scope.launch(Dispatchers.IO) {
        delay(3_000)
        log(3)
    }
    scope.launch {
        delay(2_000)
        log(2)
    }
    log(1)
    //delay(2_500)
    //scope.cancel()
    //delay(5_000)
}

fun cancelAndTimeout() = runBlocking {
    val job = launch {
        log(1)
        delay(2000)
    }
    delay(1000)
    // job.cancel()
    job.join()
    log("isCancelled=${job.isCancelled}, isCompleted=${job.isCompleted}")
}

fun cancel2() = runBlocking {
    val job = launch {
        log(1)
        var stopTime = System.currentTimeMillis()
        // 强制占用CPU，并且内部没有校验Cancel的相关方法，所以，此循环是取消不了的
        while (true) {
            if (System.currentTimeMillis() > stopTime + 500) {
                log("-----")
                stopTime = System.currentTimeMillis()
            }
        }
    }
    delay(1000)
    job.cancelAndJoin()
    log("isCancelled=${job.isCancelled}, isCompleted=${job.isCompleted}")
}

fun cancel3() = runBlocking(Dispatchers.IO) {
    val job = launch(Dispatchers.IO) {
    }
    job.cancelAndJoin()
}

fun cancel4() = runBlocking {
    val job = launch(NonCancellable) {
        try {
            repeat(1000) { i ->
                log("job: I'm sleeping $i ...")
                delay(500L)
            }
        } catch (ignore: CancellationException) {
        } finally {
            log("做一些收尾工作")

            // 会执行 不会抛异常，此时挂起函数会正常执行
            withContext(NonCancellable) { // [1]
                delay(100)
                log("一些收尾工作需要在子线程中执行。。。")
            }

            try {
                // 会执行，会抛异常JobCancellationException（CancellationException的子类）
                withContext(NonCancellable + Dispatchers.IO) { // [2]
                    delay(100)
                    log("一些收尾工作需要在子线程中执行。。。")
                }
            } catch (e: CancellationException) {
                log("抛出异常 ${e.message}")
            }

            // 不会执行，不会抛异常
            launch {} // [3]
            // 不会执行，不会抛异常
            launch(Dispatchers.IO) {} // [4]
            // 会执行，不会抛异常
            launch(NonCancellable) {} // [5]
            // 会执行，不会抛异常
            launch(NonCancellable + Dispatchers.IO) {} // [6]
        }
    }
    delay(1300L) // 延迟一段时间
    log("main: I'm tired of waiting!")
    // 取消该作业并且等待它结束
    job.cancelAndJoin()
    log("main: Now I can quit.")
}

// 用完就取消的协程 - 自创的优雅方式
fun cancel5() = runBlocking {
    fun real(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            log("-----")
            delay(1000)
            log("-----")
            coroutineScope.cancel()
            withContext(Dispatchers.IO) {
                log("-----")
            }
        }
    }

    val scope = CoroutineScope(Dispatchers.IO)
    real(scope)
    delay(2000)
    log("${scope.isActive}")
    scope.launch {
        log("OK?")
    }
    Unit
}

fun cancel6() = runBlocking {
    val job = launch {
        log("start")
        while (true) {

        }
        log("end")
    }
    job.join()
    job.cancel()
}