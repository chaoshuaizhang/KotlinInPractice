package com.example.kotlininpractice.daily.inaction

import java.util.concurrent.Callable

object TestObject {
    fun sum() = 1 + 2
}

fun main() {
    val instance = MyTestObject.getInstance1("")
    instance.myext()
    MyTestObject.ext()
    request(MyTestObject)
}
fun MyTestObject.myext(){}
class MyTestObject private constructor(type: String) {

    val tag = ""

    companion object : Callable<String> {
        fun getInstance1(type: String): MyTestObject {
            return MyTestObject(type)
        }

        fun getInstance2(type: String): MyTestObject {
            return MyTestObject(type)
        }

        override fun call(): String {
            return "response"
        }
    }
}
// 扩展属性
val MyTestObject.Companion.tag: String
    get() = ""
// 扩展方法
fun MyTestObject.Companion.ext() {
    this.tag
}
//class AS:MyTestObject(""){
//    init {
//        getInstance1("")
//    }
//}

fun <T> request(callable: Callable<T>): T {
    return callable.call()
}

