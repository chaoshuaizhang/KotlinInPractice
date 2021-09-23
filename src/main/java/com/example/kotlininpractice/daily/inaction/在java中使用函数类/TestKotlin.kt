package com.example.kotlininpractice.daily.inaction.在java中使用函数类

import com.example.kotlininpractice.daily.inaction.委托属性.Person

var myListener: () -> String = { "" }

fun kotlinCallback(cb: (String) -> Unit) {
}

fun kotlinCallback(cb: (Int, String) -> Double) {}

public object TestKotlin

class PatchedSku
class SkuPrice

fun checkPeople(): (Person) -> Boolean {
    val check = { p: Person ->
        p.name == "zcs"
    }
    return check
}
fun execute(){
    listOf<Person>().filter(checkPeople())
}