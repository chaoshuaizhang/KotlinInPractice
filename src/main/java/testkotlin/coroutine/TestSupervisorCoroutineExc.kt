package testkotlin.coroutine

import kotlinx.coroutines.*

fun main() = runBlocking {
    TestSupervisorCoroutineExc.testExc3_1()
    delay(10_000)
}

// 测试一下协程的非结构化并发
object TestSupervisorCoroutineExc {

    private val supervisorScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /**
     * 「测试非结构化并发也是会相互影响的，**并不是加了SupervisorJob后，一个
     * 协程发生异常不会影响到另一个协程**」
     * SupervisorJob的取消是单向传播->向下传播
     * Job是双向传播->向上并且向下传播
     * */
    fun testExc1() {
        // job1异常 -> 传递给supervisorScope -> supervisorScope把异常传递给自己的子协程
        supervisorScope.launch {
            val job1 = launch {
                log("--- job1即将抛出异常 ---")
                delay(1000)
                throw IllegalStateException()
            }
            val job2 = launch {
                log("--- job2开始执行 ---")
                delay(2000)
                log("--- job2执行结束 ---")
            }
        }
    }

    /**
     * 「测试非结构化协程不会在平级作用域中传递」
     * job1和job2不是上下级关系，是同级关系，所以job1的异常影响不了job2
     * job1_1和job1_2是job1的子协程，job1_1异常后，，异常会传给顶级协程，顶级协程再把异常向下传递
     * [异常传递](imgs/协程异常的传递.png)
     * */
    fun testExc2() {
        // 启动一个顶级协程 job1
        val job1 = supervisorScope.launch {
            val job1_1 = launch {
                log("--- job1即将抛出异常 ---")
                delay(1000)
                throw IllegalStateException()
            }
            val job1_2 = launch {
                log("--- job1_2开始执行（会在执行过程中由于job1_1的异常被取消） ---")
                delay(2000)
                log("--- job1_2执行结束 ---")
            }
        }
        // 启动一个顶级协程 job2
        val job2 = supervisorScope.launch {
            val job2_1 = launch {
                log("--- job2_1开始执行 ---")
                delay(2000)
                log("--- job2_1执行结束 ---")
            }
        }
    }

    /**
     * 「测试同一个顶级协程作用域中，异常是否会向上传播？」
     * */
    fun testExc3() {
        supervisorScope.launch {
            /*所以这里加个ExcHandler可以捕获job1_1的异常吗？不可以❌❌❌*/
            val job1 = launch(CoroutineExceptionHandler { _, e ->
                log("--- 异常 --- ${e.message}")
            }) {
                val job1_1 = launch {
                    log("--- job1_1开始执行，马上抛出异常 ---")
                    delay(1_000)
                    throw IllegalStateException()
                }
                val job1_2 = launch {
                    log("--- job1_2开始执行 ---")
                    delay(2_000)
                    log("--- job1_2执行结束 ---")
                }
            }
            val job2 = launch {
                log("--- job2开始执行 ---")
                delay(2_000)
                log("--- job2执行结束 ---")
            }
        }
    }

    /*
    * 「测试同一个顶级协程作用域中，异常是否会向上传播？」
    * job1_1异常后，异常会传给顶级协程，虽然顶级协程设置了ExcHandler，但此顶级协程仍然要取消掉，所以
    * job1_2会被取消。
    * 但是job2没有被取消，说明job1这个顶级协程的异常并没有传给rootJob，rootJob收不到异常，所以job2就不会被取消，还是会正常执行
    * */
    fun testExc3_1() {
        val rootJob = supervisorScope.launch {
            val job1 = supervisorScope.launch(CoroutineExceptionHandler { _, e ->
                log("--- 异常 --- ${e.message}")
            }) {
                val job1_1 = launch {
                    log("--- job1_1开始执行，马上抛出异常 ---")
                    delay(1_000)
                    throw IllegalStateException()
                }
                val job1_2 = launch {
                    log("--- job1_2开始执行 ---")
                    delay(2_000)
                    log("--- job1_2执行结束 ---")
                }
            }
            val job2 = launch {
                log("--- job2开始执行 ---")
                delay(2_000)
                log("--- job2执行结束 ---")
            }
        }
    }
}