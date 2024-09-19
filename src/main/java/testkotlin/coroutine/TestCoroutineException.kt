package testkotlin.coroutine

import kotlinx.coroutines.*

private val scope = CoroutineScope(Dispatchers.IO)

suspend fun main() = runBlocking {
    testExc4_1()
    delay(30_000)
}

// 协程是结构化并发的，协程作用域内部的一个子协程抛出异常，会把这个异常也传递给父协程
// 父协程收到异常后，会去取消作用域内的所有子协程
fun testExc1() {
    scope.launch {
        val job1 = launch {
            delay(1_000)
            throw IllegalStateException("job1 exception...")
        }
        val job2 = launch {
            delay(2_000)
            log("job2 finish...")
        }
    }
}

// 如何让子协程异常，但是：
// 1. 不影响父协程
// 2. 不影响同级的子协程

// 方式一：
// 此方式肯定是不行的，此方式只是进行了一种类似try catch的操作，只是为了不让程序崩溃，
// 但是子协程确实抛出了异常，并传递给了父协程，但是父协程进行了CoroutineExceptionHandler，所以程序不会崩溃，
// 但是协程是一定要被停止的
fun testExc2() {
    scope.launch(CoroutineExceptionHandler { _, e ->
        log("父协程收到异常信息：${e.message}")
    }) {
        val job1 = launch {
            delay(1_000)
            throw IllegalStateException("job1 exception...")
        }
        val job2 = launch {
            delay(2_000)
            log("job2 finish...")
        }
    }
}

// 方式二：
// 此种方式肯定也是不行的，现在是子协程job1内部抛出了异常，而try catch是在父协程的作用域内，没有在子协程的作用域内，
// 对比java线程的Thread.UncaughtExceptionHandler，子线程抛出了异常，主线程中的try catch能捕获吗？不能！
// 这里的job1是通过launch启动的，launch启动后，相当于底层进行了线程切换，此时try catch当然就不管用了
fun testExc3() {
    scope.launch {
        try {
            val job1 = launch {
                delay(1_000)
                throw IllegalStateException("job1 exception...")
            }
        } catch (ignore: Exception) {
        }
        val job2 = launch {
            delay(2_000)
            log("job2 finish...")
        }
    }
}

// 方式三：
// 此种方式最让人诧异！！！
// 当前协程明明设置了异常处理器，但仍不能捕获到自己内部的异常！
// 「所有子协程将其异常的处理委托给其父协程，父协程也委托给父协程，依此类推，直到根协程，
// 因此永远不会使用设置在子协程上下文中的CoroutineExceptionHandler」
// 什么意思？
// 定理一：只有根协程设置的CoroutineExceptionHandler才有效
fun testExc4() {
    scope.launch {
        val job1 = launch(CoroutineExceptionHandler { _, e ->
            log("--- 捕获job1的异常 ---")
        }) {
            delay(1_000)
            throw IllegalStateException("job1 exception...")
        }
        val job2 = launch {
            delay(2_000)
            log("job2 finish...")
        }
    }
}

// 经过testExc4，我以为：「子协程设置的CoroutineExceptionHandler，只能捕获协程的子协程」，但是也不对！！
// 比如这里job1内部的子协程抛出了异常，job1照样无法捕获
// 所以「只有根协程设置的CoroutineExceptionHandler才有效」
fun testExc4_1() {
    scope.launch {
        val job1 = launch(CoroutineExceptionHandler { _, e ->
            log("--- 捕获子协程job1的子协程的异常 ---")
        }) {
            log(this.coroutineContext)
            launch {
                log(this.coroutineContext)
                delay(1_000)
                throw IllegalStateException("子协程job1的子协程 exception...")
            }
        }
        val job2 = launch {
            delay(2_000)
            log("job2 finish...")
        }
    }
}

// 方式四：
// CoroutineExceptionHandler可以捕获哪些异常？
// 只能捕获根协程内部未被处理的异常，包括：
// 1. 根协程内部的异常
// 2. 根协程内部的子协程的异常
// 3. 根协程内部的子协程的子协程的异常
// 一旦根协程的CoroutineExceptionHandler收到了异常，那么**此根协程就算结束了，内部的所有子协程都会被取消**
//
// ## 注意这里说的，满足这个条件的前提是scope加了SupervisorJob
// ## scope还是存活的，这里要理解：scope.launch了一个顶级协程作用域，这个顶级协程作用域内发生任何异常、取消，都与scope.launch的别的协程无关
fun testExc5() {
    scope.launch(CoroutineExceptionHandler { _, e ->
        log("scope.isActive = ${scope.isActive}, exc = ${e.message}")
        scope.launch {
            // 这里scope仍然能创建新的顶级协程
            log("scope起的一个顶级协程结束了，再起一个，开始执行")
            delay(2_000)
            log("scope起的一个顶级协程结束了，再起一个，结束执行")
        }
    }) {
        val job1 = launch {
            delay(2_000)
            log("根协程的子协程 job1")
            // [2]
            //throw IllegalStateException("job1 exception...")
        }
        val job2 = launch {
            val job3 = launch {
                // [3]
                log("根协程的子协程的子协程 job3")
                throw IllegalStateException("job2的子协程 exception...")
            }
            delay(4_000)
            log("根协程的子协程 job2")
        }
        delay(3_000)
        log("根协程")
        // [1]
        // throw IllegalStateException("根协程的子协程 exception...")
    }
}

// 方式五：
// 当CoroutineExceptionHandler是在初始化CoroutineScope时设置的：
// 此时，job1抛出了异常，但是job1的顶级协程没有进行处理，此时会继续向上抛，抛给Scope
// Scope收到异常后，会停止Scope启动的所有子协程
// 这个和testExc5原理差不多
val scopeExcHandler = CoroutineExceptionHandler { _, e ->
    log("--- Scope的ExcHandler收到异常 ---")
}
val scopeWithExc = CoroutineScope(Dispatchers.IO + scopeExcHandler)
fun testExc6() {
    val job1 = scopeWithExc.launch {
        delay(1_000)
        throw IllegalStateException("第一个顶级协程作用域 抛了异常")
    }
    val job2 = scopeWithExc.launch {
        log("第二个顶级协程作用域  开始执行")
        delay(2_000)
        log("第二个顶级协程作用域  执行结束")
    }
}

// 方式六：
// 如果把异常处理ExcHandler设置到job1的顶级协程作用域中呢？
// 发现，此时，job1出现了异常，并且会影响job2
// 为什么会影响到job2？
// 因为Scope收到了异常，就如
fun testExc7() {
    val job1 = scope.launch(CoroutineExceptionHandler { _, e ->
        log("--- 第一个顶级协程设置了ExcHandler ---")
    }) {
        delay(1_000)
        throw IllegalStateException("第一个顶级协程作用域 抛了异常")
    }
    val job2 = scope.launch {
        log("第二个顶级协程作用域  开始执行")
        delay(2_000)
        log("第二个顶级协程作用域  执行结束")
    }
}

// 如果scope和job1的顶级协程都声明了ExcHandler，那么当job1发生异常时，job2也会被取消
// FUCK，什么鬼？为什么只有job1设置时不会影响到job2，Scope也设置了就会影响job2，
// 难道 被ExcHandler捕获了的异常，还会继续传递？？？
// 【疑难点】
val innerExcHandler = CoroutineExceptionHandler { _, e ->
    log("--- 第一个顶级协程设置了ExcHandler ---")
    log(scopeWithExc)
    scopeWithExc.launch {
        log("协程还在吗?  不在了")
        delay(1_000)
        log("协程还在")
    }
}

fun testExc7_1() {
    val job1 = scopeWithExc.launch(innerExcHandler) {
        delay(1_000)
        throw IllegalStateException("第一个顶级协程作用域 抛了异常")
    }
    val job2 = scopeWithExc.launch {
        log("第二个顶级协程作用域  开始执行")
        delay(2_000)
        log("第二个顶级协程作用域  执行结束")
    }
}
#################
不知从第几个实例开始，Scope被加上了SupervisorJob，，草，完全影响了测试结果
#################

// 理解什么是根协程
fun test() {
    // 根协程
    runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception in CoroutineExceptionHandler")
        }
        // 父协程「这里的handler不生效」
        launch(handler) {
            // 子协程
            launch {
                throw RuntimeException("Test Exception")
            }
        }
    }

    // 根协程
    scope.launch {
        // 父协程
        launch { }
    }
}