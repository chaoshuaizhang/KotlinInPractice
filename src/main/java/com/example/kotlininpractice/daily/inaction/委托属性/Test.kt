package com.example.kotlininpractice.daily.inaction.委托属性

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class UserTable {
    var name: String = "name"
    operator fun getValue(userDTO: UserDTO, property: KProperty<*>): String {
        // 不太清楚这里的userDTO有啥用？？
        println("对 $name  执行 query 操作")
        return ""
    }

    operator fun setValue(userDTO: UserDTO, property: KProperty<*>, s: String) {
        name = s
        println("对 $name  执行save / update操作")
    }
}

class UserTable2 : ReadWriteProperty<Nothing?, String>{
    override fun setValue(thisRef: Nothing?, property: KProperty<*>, value: String) {
        TODO("Not yet implemented")
    }

    override fun getValue(thisRef: Nothing?, property: KProperty<*>): String {
        TODO("Not yet implemented")
    }
}
// 和默认不继承ReadWrite一样
val name2:String by UserTable2()

class UserDTO {
    var name: String by UserTable()
}

var tag: Int by Delegates.observable(1, { p: KProperty<*>, a: Int, s: Int ->
    println("$a   $s")
})

fun main() {
    val dto = UserDTO()
    dto.name = "zcs"
    println(dto.name)
    tag = 1001
    var notNull: ReadWriteProperty<Any?, String> = Delegates.notNull<String>()
}