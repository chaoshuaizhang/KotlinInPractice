package testkotlin.coroutine

import kotlinx.coroutines.*

private val scope = CoroutineScope(Dispatchers.IO)

fun main() = runBlocking {
    testExc7_1()
    log(scope.isActive)
    delay(20_000)
    log(scope.isActive)
}

// 协程是结构化并发的，协程作用域内部的一个子协程抛出异常，会把这个异常也传递给父协程
// 父协程收到异常后，会去取消作用域内的所有子协程，同时也会取消自己-【根协程】
// 下面的job1内部抛出异常，异常会传递给scope，scope会取消内部的所有子协程
// 理解一下这里的子协程和根协程：
// 1. scope启动的协程，根协程：scope.launch {}
// 2. scope内部的协程，子协程：scope.launch { launch {} }
// 如何判断scope是否被取消？
// 当子协程抛出异常后，scope.isActive=false，没有抛出异常，即使子协程都执行结束了，scope.isActive仍然=true
// TODO: 注意这里的子协程、根协程的概念，看后续会不会发生变化
// 凡是scope.launch的都是根协程，如testExc4_2
fun testExc1() {
    // 子协程
    scope.launch {
        // 子协程
        val job1 = launch {
            delay(1_000)
            //throw IllegalStateException("job1 exception...")
        }
        // 子协程
        val job2 = launch {
            delay(2_000)
            log("job2 finish...")
        }
    }
    // 子协程
    scope.launch {
        delay(3_000)
        log("--- 依旧不会执行，因为scope都被取消了 ---")
    }
}

// 如何让子协程发生异常，但是：
// 1. 不影响父协程
// 2. 不影响同级的子协程

// 方式一 testExc2：
// 此方式肯定是不行的，此方式只是进行了一种类似try catch的操作，只是为了不让程序崩溃，
// 但是子协程确实抛出了异常，并传递给了父协程，只是因为父协程进行了CoroutineExceptionHandler，所以程序不会崩溃，
// 但是协程是一定要被停止的，
// 所以，job1发生异常后，scope.isActive=false了，所以job2不会被执行
fun testExc2() {
    scope.launch(CoroutineExceptionHandler { _, e ->
        log("父协程收到异常信息：${e.message}")
    }) {
        val job1 = launch {
            delay(1_000)
            throw IllegalStateException("job1 exception...")
        }
        // 不会被执行
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
// 【定理一】：只有根协程设置的CoroutineExceptionHandler才有效
// 注意定理一指的是根协程，而不是父协程，testExc4_1更能说明问题
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
            // TODO: 注意，this.coroutineContext 和 this@innerLaunch.coroutineContext都包含了CoroutineExceptionHandler
            // 但是仍然无法捕获到异常。。。很奇怪
            log(this.coroutineContext)
            launch innerLaunch@{
                log(this@innerLaunch.coroutineContext)
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

// 什么是根协程？
// 凡是scope.launch的都是根协程！！！
// 但凡有协程抛出异常，不管是子协程还是根协程，scope都会被取消
fun testExc4_2() {
    scope.launch(CoroutineExceptionHandler { _, e ->
        log("--- 这是一个根协程，捕获子协程的异常 ---")
    }) {
        // 内部的根协程抛出异常，照样会让scope取消
        val job1 = scope.launch(CoroutineExceptionHandler { _, e ->
            log("--- 这也是一个根协程，其是根协程内部的根协程，捕获子协程的异常 --- ：${e.message}")
        }) {
            launch innerLaunch@{
                delay(1_000)
                throw IllegalStateException("子协程job1的子协程 exception...")
            }
        }
        val job2 = launch {
            try {
                delay(2_000)
            } catch (e: Exception) {
                log("--- job2当然会收到异常了, ${e.message} ---")
            }
            log("job2 finish...")
        }
    }
}

// 「只有根协程设置的CoroutineExceptionHandler才有效」
// 这里涉及到了一个问题为什么testExc4中的子协程设置的ExcHandler无法捕获异常，而这里testExc4_3中
// scope设置的ExcHandler可以捕获异常？
// 因为这里的ExcHandler是设置在根协程上了
// 本实例对应的是testExc5的[3]
fun testExc4_3() {
    scope.launch(CoroutineExceptionHandler { _, e ->
        log("父协程收到异常信息：${e.message}")
    }) {
        delay(1_000)
        throw IllegalStateException("job1 exception...")
    }
}

// 方式四：
// 「CoroutineExceptionHandler可以捕获哪些异常？」
// 只能捕获根协程内部未被处理的异常，包括：
// 1. 根协程内部的异常
// 2. 根协程内部的子协程的异常
// 3. 根协程内部的子协程的子协程的异常
// 一旦根协程的CoroutineExceptionHandler收到了异常，那么**此根协程就算结束了，内部的所有子协程都会被取消**
//
// TODO: 这里##两句话不该出现在这里
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
// Scope收到异常后，会停止Scope启动的所有子协程和根协程
// 这个和testExc5原理差不多
val scopeExcHandler = CoroutineExceptionHandler { _, e ->
    log("--- Scope的ExcHandler收到异常 --- ${e.message}")
}
val scopeWithExc = CoroutineScope(Dispatchers.IO + scopeExcHandler)
fun testExc6() {
    val job1 = scopeWithExc.launch {
        delay(1_000)
        // 这里抛出的异常会被scopeWithExc中的ExcHandler捕获，也就是「scopeExcHandler」
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
// 为什么会影响到job2？ 因为Scope同样收到了异常，即使ExcHandler设置在了job1的顶级协程中，不管在哪，都是scope相关的作用域？
// testExc7和testExc6 意义一样
fun testExc7() {
    // 和上面的testExc4_3一样
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
// 难道 被ExcHandler捕获了的异常，还会继续传递？？？
// 这并不是传递的问题：只要scope启动的协程/根协程/子协程有一个发生异常，scope就会被取消
fun testExc7_1() {
    // scopeWithExc本身已经携带了ExcHandler，launch时又新创建了一个ExcHandler
    val job1 = scopeWithExc.launch(CoroutineExceptionHandler { _, e ->
        log("--- 第一个顶级协程设置了ExcHandler --- ${e.message}")
        log(scopeWithExc)
        scopeWithExc.launch {
            log("协程还在吗?  不在了")
            delay(1_000)
            log("协程还在")
        }
    }) {
        delay(1_000)
        throw IllegalStateException("第一个顶级协程作用域 抛了异常")
    }
    val job2 = scopeWithExc.launch {
        log("第二个顶级协程作用域  开始执行")
        delay(2_000)
        log("第二个顶级协程作用域  执行结束")
    }
}

// 理解什么是根协程
fun testRootCoroutine() {
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