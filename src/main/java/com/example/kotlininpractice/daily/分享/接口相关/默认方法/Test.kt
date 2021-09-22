package com.example.kotlininpractice.daily.分享.接口相关.默认方法

internal interface ITest {
    fun defFun() {
        println("default fun ~")
    }
}

class Test : ITest {
    fun doSth() {
        defFun()
    }
}