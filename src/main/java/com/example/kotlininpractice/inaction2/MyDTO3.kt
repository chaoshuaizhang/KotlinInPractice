package com.example.kotlininpractice.inaction2

class MyDTO3(val name: String, val age: Int) : Cloneable {
    fun mClone(): MyDTO3 {
        return clone() as MyDTO3
    }
}

data class MyDTO4(val sex: String, val dtO3: MyDTO3) : Cloneable

fun main() {
    val dto3 = MyDTO3("zcs", 25)
    val dto4 = MyDTO4("boy", MyDTO3("zcs", 25))
    val dtoCopy = dto4.copy(sex = "girl")
    dto3.mClone()
}
