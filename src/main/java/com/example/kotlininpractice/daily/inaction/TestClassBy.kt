package com.example.kotlininpractice.daily.inaction

class MyExtList<T>(private val innerList: List<T> = ArrayList<T>()) : List<T> by innerList {
    // 扩展的方法
    fun myListSize(): Long {
        return innerList.size + 1L
    }
}