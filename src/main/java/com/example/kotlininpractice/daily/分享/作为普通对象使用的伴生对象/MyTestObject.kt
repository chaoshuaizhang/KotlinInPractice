package com.example.test.分享.作为普通对象使用的伴生对象

import java.util.concurrent.Callable

class MyTestObject private constructor() {

    companion object : Callable<String> {
        override fun call(): String {
            return "response"
        }
    }
}

fun <T> request(callable: Callable<T>): T {
    return callable.call()
}

fun main() {
    request(MyTestObject)
    request(MyTestObject.Companion)
}