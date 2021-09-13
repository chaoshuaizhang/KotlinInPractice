package com.example.kotlininpractice.daily.inaction

/*
* 手写kotlin的一些扩展函数
* */
fun main() {
    val list = listOf(3, 2, 6, 8, 1001, 98, 12, 0, 23, 64)
    val maxValue = list.myMaxBy {
        it
    }
    println(maxValue)
}

/*
* 模仿kotlin自带的maxBy函数
* */
fun <T> List<T?>.myMaxBy(condition: (T?) -> Int?): T? {
    if (isEmpty()) return null
    if (size == 1) return get(0)
    var tmp: T? = get(0)
    for (i in 0 until size - 1) {
        val left = condition(tmp) ?: continue
        val right = condition(get(i + 1)) ?: continue
        tmp = if (left >= right) tmp
        else get(i + 1)
    }
    return tmp
}

fun findMax():Int{
    return 0
}

var a = findMax()