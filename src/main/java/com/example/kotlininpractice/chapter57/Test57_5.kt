package com.example.kotlininpractice.chapter57

fun main() {
    test1(1){_->

    }
}

fun test1(a:Int, cb:()->Unit){
    println(1)
}
    fun test1(a:Int, cb:(Int)->Unit){
    println(2)
}
fun test1(a:Int, cb:(Int,Int)->Unit){
    println(3)
}