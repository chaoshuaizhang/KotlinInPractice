package com.example.test.分享.方法默认值

open class MyDTO {
    companion object {
        val instance = MyDTO()
    }
}
val tag: String by lazy {
    ""
}
class MyDTO4(val name: String = "zcs", age: Int = 15, var sex: String, referenceType: MyDTO = MyDTO.instance) {

}

fun main() {
    val dto = MyDTO4(sex = "boy")
    var i = 1
    println(i-- > 0)
}