package com.example.kotlininpractice.chapter56

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.css.RGBColor
import kotlin.math.ceil
import kotlin.math.roundToInt

val base64StrLength = 36.5f * 1024 * 1024
private const val SIZE_TARGET = 1024 * 1365f

fun base64_(length: Float): String {
    if (length < SIZE_TARGET) return "$base64StrLength"
    val tmp = ceil(SIZE_TARGET / base64StrLength * 100).toInt()
    return base64_(tmp.toFloat())
}

fun main() {
    val d = 10 / 0.0
//    val vo1 = VO().apply {
//        a = 1
//        s = "tag-1"
//    }
//
//    val vo2 = VO().apply {
//        a = 2
//        s = "tag-2"
//    }
//
//    val vo3 = VO().apply {
//        a = 3
//        s = "tag-3"
//    }

//    val list1 = mutableListOf<VO>(vo1)
//    val list2 = mutableListOf<VO>(vo2)
//    val list3 = mutableListOf<VO>(vo3)
//    val  listAll = mutableListOf<VO>()
//    listAll.addAll(list1)
//    listAll.addAll(list2)
//    listAll.addAll(list3)
//    listAll[1].s = "1008611"
//    println(list2[0].s)
//    val l: Map<Int, Int> = listAll.withIndex().associate {
//        it.value.a to it.index
//    }

//    val s:String? = null
//    val a = 1
//    println(s is String)
//
//    for (i in 9 until 9){
//        println(i)
//        val ll: String = i.run {
//            ""
//        }
//    }

//    base64_(base64StrLength)
//
//    val a = 2001.62
//    println(ceil(a))
//
//    val sb = StringBuffer()
//    sb.append("1")
//    sb.append("1")
//    sb.append("1")
//    sb.append("+")
//    val lll = sb.removeRange(sb.length - 1, sb.length)
//    println(lll)
//
//    val q: String? = "1"
//
//    when {
//        q?.contains("1") ?: false -> {
//            println("---------------------------------")
//        }
//    }
//
//    val s = "6PBFQcGBqbXd77H8"
//    val array = byteArrayOf(56, 72, 55, 55, 100, 88, 98, 113, 66, 71, 99, 81, 70, 66, 80, 54)
//    println("初始数组")
//    array.forEach {
//        print("${it.toInt()}, ")
//    }
//    println()
//    // 0111101100000010
//    // 0100000011011110
//    val map = 31490
//    val newArr = array.clone()
//    "0111101100000010".forEachIndexed { index, c ->
//        val lll: Boolean = c == '0'
//        if (lll) {
//            newArr[index] = (newArr[index].toInt() + 32).toByte()
//        }
//    }
//    println()
//    println("转换为大写数组")
//    newArr.forEach {
//        print("${it.toInt()}, ")
//    }
//    println()
//    array.forEach { print("$it ") }
//    for (i in array.indices) {
//        val tag = map.shr(i) and 1
//        if (tag < 1) array[i] = (array[i] - 32).toByte()
//    }
//    println()
//    println("重新计算完成")
//    array.forEach { print("$it, ") }
//    println()
//    println(String(array))
//
//    val excHandle = CoroutineExceptionHandler { context, _ ->
//
//    }
//    GlobalScope.launch {
//
//        launch() {
//
//        }
//
//        coroutineScope {
//
//        }
//
//    }
}

fun getGrammarKey(): String {
    val next = byteArrayOf(108, 57, 67, 89, 105, 105, 56, 51, 83, 108, 90, 115, 69, 119, 113, 87, 35, 87, 54, 114)
    return String(next)
}

private fun generateQueryKey(): String {
    val map = 1614596
    val next = byteArrayOf(119, 118, 99, 104, 119, 110, 114, 105, 101, 104, 119, 111, 49, 108, 98, 98, 109, 103, 115, 106, 117, 119, 61, 61)
    for (i in next.indices) {
        if ((map and (1 shl i)) != 0) {
            next[i] = (next[i] - 32).toByte()
        }
    }
    return String(next)
}

private fun generateSignKey(): String {
    val map = 37389
    val next = byteArrayOf(107, 122, 115, 109, 114, 109, 118, 102, 99, 121, 98, 117, 121, 107, 117, 110)
//    for (i in next.indices) {
//        if ((map and (1 shl (15 - i))) != 0) {
//            next[i] = (next[i] - 32).toByte()
//        }
//    }
    return String(next)
}

class VO{
    var a:Int = 0
    var s:String = ""
}