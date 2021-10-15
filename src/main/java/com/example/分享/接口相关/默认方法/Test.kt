package com.example.test.分享.接口相关.默认方法

internal interface ITest {
    fun defFun(tag: String) {
        println("$tag : default fun ~")
    }
}

class Test : ITest

fun main() {
    val test = Test()
    test.defFun("kotlin-tag")
}