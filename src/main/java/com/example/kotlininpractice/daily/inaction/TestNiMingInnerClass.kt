package com.example.kotlininpractice.daily.inaction

import java.util.concurrent.Callable

fun main() {
    var testMyTag = 1
    val list = listOf("")
    list.forEach {
        testMyTag++
    }
    list.maxBy { it.length }
//    addListener(object : IListener {
//        override fun click() {
//            tag = 9
//        }
//        override fun delete() {}
//        override fun add() {}
//    })


//    addListener(object : IListener4() {
//        override fun query() {
//            super.query()
//        }
//    })

    addListenerr(Callable<String> {
        TODO()
    })
    addListener(object : IListener22 {
        override fun delete() {

        }
    })

}

fun addListener(listener: IListener22) {}
fun addListenerr(listener: Callable<String>) {}
//fun addListener(listener: IListener4) {}


interface IListener : IListener2, IListener3 {
    fun click()
}

interface IListener2 {
    fun delete()
}
interface IListener22 {
    fun delete()
}
interface IListener3 {
    fun add()
}

abstract class IListener4: IListener5(){
    open fun query(){}
}
abstract class IListener5 {
    open fun query5(){}
}
abstract class IListener6 {
    open fun query6(){}
}