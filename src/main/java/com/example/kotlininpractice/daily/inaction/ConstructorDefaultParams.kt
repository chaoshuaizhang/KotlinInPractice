package com.example.kotlininpractice.daily.inaction

open class MyDTO0 {

    constructor()
    constructor(name: String)
//    constructor(name: String, age: Int)

    companion object {
        val instance = MyDTO0()
    }
}

class MyDTO00 : MyDTO0 {
    constructor():super("")
//    constructor(name: String)
//    constructor(name: String, age: Int)

}

class MyDTO1(val name: String = "zcs") : MyDTO0()

class MyDTO2(val name: String = "zcs", age: Int = 15)

class MyDTO3(val name: String = "zcs", age: Int = 15, val sex: String)

class MyDTO4(val name: String = "zcs", age: Int = 15, var sex: String, referenceType: MyDTO0 = MyDTO0.instance)

fun main() {
    val dto0 = MyDTO0()
    val dto1 = MyDTO1()
    val dto2 = MyDTO2()
    val dto3 = MyDTO3(sex = "boy")
    val dto4 = MyDTO4(sex = "boy")
}