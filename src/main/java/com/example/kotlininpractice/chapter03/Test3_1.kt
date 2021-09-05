package com.example.kotlininpractice.chapter03

fun main() {
    println("使用命令行编译.kt文件")
    Test3_1.test1()
}

object Test3_1:Test3_2() {

}

open class Test3_2{

fun test1(){}

}