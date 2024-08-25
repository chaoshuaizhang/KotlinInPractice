package org.example.test

interface IKotlin {
    fun doSth()
}

class KotlinImpl : IKotlin {

    init {
        println("不该执行 " + hashCode())
    }

    override fun doSth() {
        println("do sth ...")
    }
}

class TestKotlin(test: () -> IKotlin) : IKotlin by test.invoke() {

    init {
        test.invoke()
    }

}

class A {
    val testKotlin by lazyOf {
        KotlinImpl()
    }
}

fun main() {
    println("hashCode: " + A().hashCode())
}