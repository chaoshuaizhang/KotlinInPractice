package com.example.kotlininpractice.daily.inaction.委托属性

import kotlin.reflect.KProperty

class UserTable {
    var name: String = "name"
    operator fun getValue(userDTO: UserDTO, property: KProperty<*>): String {
        println("对 $name  执行 query 操作")
        return ""
    }

    operator fun setValue(userDTO: UserDTO, property: KProperty<*>, s: String) {
        name = s
        println("对 $name  执行save / update操作")
    }
}

class UserDTO {
    var name: String by UserTable()
}

fun main() {
    val dto = UserDTO()
    dto.name = "zcs"
    println(dto.name)
}