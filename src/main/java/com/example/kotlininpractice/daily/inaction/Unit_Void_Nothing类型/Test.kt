package com.example.kotlininpractice.daily.inaction.Unit_Void_Nothing类型

import java.lang.NullPointerException
import java.util.concurrent.Callable
import kotlin.random.Random

fun main() {
    addCallbackUnit(Callable {
    })
    // 编译不通过
//    addCallbackVoid(Callable<Void> {
//        return@Callable
//    })
    addCallbackNothing(object : Callable<Nothing> {
        override fun call(): Nothing {
            TODO("Not yet implemented")
        }
    })
    val tmp: String = getResult() ?: checkIsRight()
    println(tmp.length)
}

fun addCallbackUnit(cb: Callable<Unit>) {}
fun addCallbackVoid(cb: Callable<Void>) {}
fun addCallbackNothing(cb: Callable<Nothing>) {}
fun getResult(): String? {
    return null
}

fun checkIsRight(): Nothing {
    throw IllegalAccessException()
}