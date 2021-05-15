package com.example.kotlininpractice.chapter04

fun main() {
    val v1: Int? = getIntValue("12")
    val v2: Int? = getIntValue("5")
    // TODO 首先直接写 v1 * v2是不能编译通过的，因为v1和v2是可空类型
    // println(v1 * v2)
    if (v1 == null) {
        println("NULL Param ...")
    } else if (v2 == null) {
        println("NULL Param ... ")
    } else {
        // 但是这里就没有问题，因为经过上面的非空校验后，kotlin已经可以推断出v1和v2肯定非空了
        // 所以这里就可以安全的进行乘法运算了
        println(v1 * v2)
    }
}

fun getIntValue(s: String): Int? {
    return try {
        s.toInt()
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}