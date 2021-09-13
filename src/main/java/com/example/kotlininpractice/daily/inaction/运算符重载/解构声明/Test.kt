package com.example.kotlininpractice.daily.inaction.运算符重载.解构声明

import com.example.kotlininpractice.daily.inaction.运算符重载.ColorPoint
import com.example.kotlininpractice.daily.inaction.运算符重载.Point
import java.awt.Canvas

fun main() {
    val p = Point(1, 2f)
    val (x, y) = p

    val (q, w, e) = ColorPoint(1, 2, 3)
}