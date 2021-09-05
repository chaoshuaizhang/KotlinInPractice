package com.example.kotlininpractice.daily.inaction

import kotlin.random.Random

interface InterfaceWithParams {
    val name: String
    val age: Int
}

class WithParamsImpl1(override val name: String, override val age: Int) : InterfaceWithParams

class WithParamsImpl : InterfaceWithParams {
    override val name: String
        get() {
            return if(BuildConfig.isDebug) "zcs" else "666"
        }
    override val age: Int
        get() {
            return Random.nextInt(10000)
        }
}

fun main() {
    val impl1 = WithParamsImpl1("ooo", Random.nextInt(10000))
    val impl = WithParamsImpl()
    println(impl.age)
    println(impl.age)
    println(impl.age)
    println("==============")
    println(impl1.age)
    println(impl1.age)
}