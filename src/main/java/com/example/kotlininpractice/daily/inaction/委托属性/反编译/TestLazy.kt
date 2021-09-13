package com.example.kotlininpractice.daily.inaction.委托属性.反编译

import kotlin.reflect.KProperty

class OrderDTO {
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
        return "MMKV的值"
    }
    operator fun setValue(nothing: Nothing?, property: KProperty<*>, s: String) {
        val setMMKV = "设置MMKV的值"
    }
}

fun main() {
    val order by lazy {
        OrderDTO()
    }
}