package com.example.kotlininpractice.daily.inaction

import java.util.*
import java.util.concurrent.Callable
import java.util.function.Consumer
import javax.swing.JButton

fun addCart(cardNo: String) {}
fun myCallback(cb: Callable<String>) {
    cb.call()
}

class MyView {
    fun addCallback(cb: Callable<String>) {}
    fun addClick(listener: MyListener) {}
}

fun main() {
    val cb = ::myCallback
    cb.invoke(Callable<String> {
        TODO()
    })
    val view = MyView()
    view.addClick(MyListener { TODO("Not yet implemented") })
    view.addCallback(Callable {
        TODO()
    })
    val button = JButton()
    button.addActionListener {

    }
    val viewCb = MyView::addCallback
    viewCb.invoke(view, Callable {
        TODO()
    })


    val cartFun = ::addCart
    cartFun.invoke("")
    ArrayList<String>().forEach(System.out::println)
    ArrayList<String>().forEach(Consumer {

    })
    var testMyTag = 1
    val list = listOf("")
    list.forEach {
        testMyTag++
    }
    executeCallback {
        testMyTag--
    }
}

val action = { i: Int, s: String -> sendEmail(i, s) }
val action2 = ::sendEmail
fun sendEmail(i: Int, s: String): String {
    TODO("Not yet implemented")
}

fun executeCallback(cb: () -> Unit) {}