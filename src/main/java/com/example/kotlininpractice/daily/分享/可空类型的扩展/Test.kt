package com.example.test.分享.可空类型的扩展

import com.example.test.分享.Word
import kotlin.reflect.KProperty

fun String?.ext() {
    val list = listOf<String>()

}


fun Word?.ext() {
    println(this?.trans)
}

fun main() {
//    println(name)
    name = "a"
//    println(String::class.java.typeName)
//    val word = Word()
//    word.ext()
}



class DelegatePerson {
    private var _name = ""

    operator fun getValue(person2: Nothing?, property: KProperty<*>): String {
        println("get")
        return _name
    }

    operator fun setValue(person2: Nothing?, property: KProperty<*>, s: String) {
        println("set")
        _name = s
    }
}

var name: String by DelegatePerson()