package com.example.test.分享.局部函数

class Test {
    fun fetchSubConfigValue(key: String, callback: (String) -> Unit) {
        fun value() = request("key")
        value().let {
            if (it.trim().isEmpty()) {
                repeatRefresh { callback(value()) }
            } else {
                callback(it)
            }
        }
    }

    private fun request(key: String) = ""

    private fun repeatRefresh(callback: (String) -> Unit){
        callback.invoke("value")
    }
}

fun main() {
    val test = Test()
    test.javaClass.declaredMethods.forEach {
        it.isAccessible = true
        println(it.name)
    }
}