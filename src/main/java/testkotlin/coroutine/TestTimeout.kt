package testkotlin.coroutine

import kotlinx.coroutines.*
import java.time.LocalDateTime

fun main() = testTimeout4()

fun testTimeout1() = runBlocking {
// 注意，超时会抛出[TimeoutCancellationException]异常
    withTimeout(600L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

}

//sampleStart
var acquired = 0

class Resource {
    init {
        acquired++
    } // Acquire the resource

    fun close() {
        acquired--
    } // Release the resource
}

fun testTimeout2() {
    runBlocking {
        repeat(10000) { // Launch 10K coroutines
            launch {
                val resource = withTimeout(60) { // Timeout of 60 ms
                    delay(50) // Delay for 50 ms
                    Resource() // Acquire a resource and return it from withTimeout block
                }
                resource.close() // Release the resource
            }
        }
    }
    // Outside of runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}
//sampleEnd

// 异常不会抛出
fun testTimeout3() {
    runBlocking {
        launch {
            val constructor = TimeoutCancellationException::class.java.getConstructor(String::class.java)
            constructor.isAccessible = true
            // 不会抛
            throw constructor.newInstance("此异常是internal的，无法访问")
            // 会抛
            // throw IllegalStateException()
        }
    }
}

fun testTimeout4() {
    runBlocking {
        repeat(10000) { // Launch 10K coroutines
            launch {
                var resource: Resource? = null
                try {
                    withTimeout(60) { // Timeout of 60 ms
                        delay(50) // Delay for 50 ms
                        resource = Resource() // Acquire a resource and return it from withTimeout block
                    }
                } finally {
                    resource?.close() // Release the resource
                }
            }
        }
    }
    // Outside of runBlocking all coroutines have completed
    println(acquired) // Print the number of resources still acquired
}
