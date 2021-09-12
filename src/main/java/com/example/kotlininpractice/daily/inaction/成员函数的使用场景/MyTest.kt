package com.example.kotlininpractice.daily.inaction.成员函数的使用场景

val hash: (String) -> Int = { s: String -> s.hashCode() }
val filterDauCounter: (String) -> Boolean = { s: String -> "jp,us".contains(s, false) }
fun main() {

}

class MyTest {
    fun getHash(string: String): Int {
        return string.hashCode()
    }

    val keyFun: (String) -> Boolean = { s: String -> s.hashCode() > 0 }

    fun test() {
        val key = "vnfjvnfh"
        val hash1 = getHash(key)
        val list = listOf("")
        var count: Int = list.count(filterDauCounter)
        var filter: List<String> = list.filter(filterDauCounter)
        var any: Boolean = list.any(filterDauCounter)

        val map = mapOf<MyTest, String>()
        var kProperty0 = ::hash


    }
}