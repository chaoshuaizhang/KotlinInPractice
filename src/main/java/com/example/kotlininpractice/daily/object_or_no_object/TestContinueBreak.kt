package com.example.kotlininpractice.daily.object_or_no_object

fun main() {

    val str1 = "aa"
    val str2 = "bb"

    for (i in 0 until 10) {
        if("$str1$str2" == "cac"){
            break
        }
    }

}