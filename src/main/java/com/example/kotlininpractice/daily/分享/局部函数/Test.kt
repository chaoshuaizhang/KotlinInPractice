package com.example.kotlininpractice.daily.分享.局部函数

class Test {
    private fun aaa() {
        fun bbb(s: String?) {
            if (s == null) throw NullPointerException()
            s.length.takeIf { true }
            fun ccc() {}
        }
        bbb("ab")
        bbb("b")
    }
}

fun main() {
    val test = Test()
    test.javaClass.declaredMethods.forEach {
        it.isAccessible = true
        println(it.name)
    }
}