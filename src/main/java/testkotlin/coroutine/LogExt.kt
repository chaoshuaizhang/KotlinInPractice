package testkotlin.coroutine

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private val dataTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS ")

fun log(msg: Any?) {
    val now = LocalDateTime.now()
    kotlin.io.println(dataTimeFormatter.format(now) + Thread.currentThread().name + ": $msg")
}

fun println(msg: Any?) {
    val now = LocalDateTime.now()
    kotlin.io.println(dataTimeFormatter.format(now) + Thread.currentThread().name + ": $msg")
}