package testkotlin.coroutine

import kotlinx.coroutines.*

fun main() {
    // 要匹配的字符串
    val input = "zoom_2024-19-14"

    // 匹配 yyyy-MM-dd 日期格式的正则表达式
    // \d{4} 表示匹配4个数字，即年份
    // \d{2} 表示匹配2个数字，即月份和日期
    val regex = Regex("""\d{4}-\d{2}-\d{2}""")

    // 查找匹配的结果
    val matchResult = regex.find(input)

    // 提取并处理匹配结果
    val date = matchResult?.value

    // 显示结果
    if (date != null) {
        println("Extracted date: $date")
    } else {
        println("No date found in input string.")
    }
}


//suspend fun main() = testAsync6()

fun testAsync1() = runBlocking {
    val deferred1 = async(start = CoroutineStart.LAZY) {
        async1()
    }
    val deferred2 = async {
        async2()
    }
    log("---")
    log("${deferred1.start()} - " + "${deferred2.start()}")
    log(deferred1.await() + deferred2.await())
    log("---")
}

suspend fun async1(): Int {
    delay(1_000)
    return 1
}

suspend fun async2(): Int {
    delay(1500)
    return 2
}

fun testAsync2() = runBlocking(CoroutineExceptionHandler { _, e ->
    e.printStackTrace()
}) {
    failedConcurrentSum()
//    try {
//        failedConcurrentSum()
//    } catch (e: ArithmeticException) {
//        println("Computation failed with ArithmeticException")
//    }
}

suspend fun failedConcurrentSum() = coroutineScope {
    val one = async {
        try {
            delay(30_000) // 模拟一个长时间的运算
            42
        } finally {
            log("First child was cancelled")
        }
    }
    // two执行后会立即抛出异常并自动取消，此时one也会被立即取消
    // 通过给two接个SupervisorJob()，即可实现结构化并发->two取消时不影响one
    try {
        val two = async<Int>(CoroutineExceptionHandler { _, e ->
            log("此处无法捕获到异常")
            //e.printStackTrace()
        }) {
            log("Second child throws an exception")
            throw ArithmeticException()
        }
        two.await()
    } catch (e: Exception) {
        log("try catch 异常 ${e::class}")
    }
    log("start wait ...")
//    val result: Int = one.await()
//    log("result=$result, end wait ...")
}

suspend fun testAsync3() {
    runBlocking(CoroutineExceptionHandler { _, e ->
        log("捕获到异常")
    }) {
        log("----------")
        val one = async {
            try {
                delay(3000) // 模拟一个长时间的运算
                42
            } finally {
                log("First child was cancelled")
            }
        }
        CoroutineScope(Dispatchers.IO).launch(CoroutineExceptionHandler { _, e ->
            e.printStackTrace()
            log("异常")
        }) {
            log("Second child throws an exception")
            throw ArithmeticException()
        }
        log("start wait ...")
        val result: Int = one.await()
        log("result=$result, end wait ...")
    }
}

suspend fun testAsync4() = runBlocking {
    val deferred = async {
        throw IllegalStateException("Test async exc...")
    }
    try {
        deferred.await()
    } catch (e: Exception) {
        log("这里虽然捕获了。。。但是协程依然会抛出异常，也就是此异常还会被抛出")
        log(" - 这是因为：异常虽然被捕获了，但是协程确实是被停止了，子协程的停止肯定要影响父协程，所以，父协程也要收到同样的异常")
        log("捕获了${e::class.java}。。。")
    }
}

suspend fun testAsync5() = runBlocking {
    val deferred = async(SupervisorJob()) {
        throw IllegalStateException("Test async exc...")
    }
    try {
        deferred.await()
    } catch (e: Exception) {
        log("这里捕获了。。。协程不会抛出异常，也就是此异常不会被抛出，因为用了SupervisorJob")
        log("捕获了${e::class.java}。。。")
    }
}

suspend fun testAsync6() = runBlocking(CoroutineExceptionHandler { _, e ->
    log("自定义ExcHandler，${e::class.java}")
}) {
    val deferred = async(SupervisorJob() + CoroutineExceptionHandler { _, e ->
        log("自定义ExcHandler2，${e::class.java}")
    }) {
        throw IllegalStateException("Test async exc...")
    }
    deferred.await()
    Unit
}

private val scope = CoroutineScope(SupervisorJob() + CoroutineExceptionHandler { _, e ->
    log("---exc handler---")
})

fun testAsync7() {
    scope.launch {
        val deferred = async {
            throw IllegalStateException("---exc---")
        }
        try {
            deferred.await()
        } catch (e: Exception) {
        }
    }
    Thread.sleep(10_000)
}