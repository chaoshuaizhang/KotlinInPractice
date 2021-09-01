package com.example.kotlininpractice.chapter76

import kotlinx.coroutines.asContextElement
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

val tl = ThreadLocal.withInitial { "initial" }
fun main() = runBlocking {
    println(tl.get()) // Will print "initial"
    // Change context
    withContext(tl.asContextElement("modified")) {
        println(tl.get()) // Will print "modified"
    }
    // Context is changed again
    println(tl.get()) // <- WARN: can print either "modified" or "initial"
}