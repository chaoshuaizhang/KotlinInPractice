package com.example.kotlininpractice.daily.inaction.运算符重载

import com.example.kotlininpractice.daily.inaction.测试javakotlin注册接口的区别.MySingleClickJListener
import java.awt.Canvas

class Point(val x: Int, val y: Float) {
    val listener = MySingleClickJListener { TODO("Not yet implemented") }
    val a = ""
    val s = ""
    val d = ""
    val z = ""
    operator fun component1() = x
    operator fun component2() = y
    operator fun component3() = z
    operator fun component4() = a
    operator fun component5() = d
    operator fun component6() = s
}

fun a():Triple<String,String,String>{
    return Triple("","","")
}

data class ColorPoint(val color: Int, val w: Int, val h: Int) {
    val canvas: Canvas? = null
}