package com.example.kotlininpractice.inaction2

import java.util.*
import java.util.concurrent.Callable
import java.util.regex.Pattern
import kotlin.concurrent.thread


class MyDTO3(val name: String, val age: Int) : Cloneable {
    fun mClone(): MyDTO3 {
        return clone() as MyDTO3
    }
}

fun defaultCountry(): String {
    val localeTmp: String = Locale.getDefault().country ?: ""
    return if (localeTmp.matches(Regex("^[A-Za-z]{3}$"))) {
        localeTmp
    } else {
        val country = Locale.getDefault().getDisplayCountry(Locale.ENGLISH)
        Locale.getISOCountries().forEach {
            if (country.equals(Locale("", it).getDisplayCountry(Locale.ENGLISH))) {
                return it
            }
        }
        "ee"
    }
}

data class MyDTO4(val sex: String, val dtO3: MyDTO3) : Cloneable
val o = String()
fun juc() {
    println("zzzzzzzzz")
    synchronized(o){
        println("cccccccccc")
        thread {
            println("----- start")
            Thread.sleep(10_000)
            println("----- end")
        }
        println("ssssssssss")
    }
}

fun main() {
    Locale.getISOCountries().forEach {
        val countryTmp = Locale("", it).getDisplayCountry(Locale.ENGLISH)
//        if (country.equals(countryTmp)) {
        println("$it     $countryTmp")
//            return@forEach
//        }
    }
}
fun qmain() {

    juc()
    juc()

//    while (true){}

//    val f = mutableListOf(5.0f, 5.5f, 6.0f)
//    println(5.0 in f)
//
//    defaultCountry()
//    // ^\d{3}$
////    println( "".matches(Regex("^[A-Da-z]{3}$")))
//
    val country = Locale.getDefault().getDisplayCountry(Locale.ENGLISH)
    Locale.getISOCountries().forEach {
//        val countryTmp = Locale("", it).getDisplayCountry(Locale.ENGLISH)
//        if (country.equals(countryTmp)) {
            println("]]]]]]]]]]]]]]]]]]]]]]]]    $it   $country")
//            return@forEach
//        }
    }
//
//
//    println(TimeZone.getDefault().displayName)
//    println(Locale.getDefault().country)
//    println(Locale.getDefault().displayCountry)
//    println(Locale.getDefault().getDisplayCountry(Locale.ENGLISH))
//    println("============================")
//    Locale.getISOCountries().forEach {
//        val l = Locale("", it)
////        println("+=+=+=  $it              ${l.getDisplayCountry(Locale.ENGLISH)}")
//    }
//    println("=====================================================================")
//
//    Locale.getAvailableLocales().forEach {
//        if (it.language.contains("ur") || it.language == "bn") {
//            println("")
//        }
//    }
//    var forLanguageTag = Locale.forLanguageTag("en")
//    val list = listOf<String>("a", "s", "d")
//    println(list.any {
//        println("=====")
//        it == "a"
//    })
//
//
//    val dto3 = MyDTO3("zcs", 25)
//    val dto4 = MyDTO4("boy", MyDTO3("zcs", 25))
//    val dtoCopy = dto4.copy(sex = "girl")
//    dto3.mClone()
//    // Indian/Chagos
//    // Asia/Urumqi
//
//    TimeZone.getTimeZone("Asia/Kolkata").apply {
//        println(this)
//        println(getDisplayName(false, TimeZone.SHORT))
//        println(id)
//        println(rawOffset)
//        println(this.toZoneId().rules.transitions.firstOrNull()?.offsetAfter?.totalSeconds)
//    }
//    println("-------------------------------------------------------------")
//    println(TimeZone.getDefault().displayName)
//    println("-------------------------------------------------------------")
//    TimeZone.getAvailableIDs().forEach {
////         println(it)
//    }
//    request(MyTestObject)
}

class MyTestObject private constructor() {
    // object类也可以实现接口
    companion object : Callable<String> {
        fun getInstance(): MyTestObject {
            return MyTestObject()
        }

        override fun call(): String {
            return "response"
        }
    }
}

// 定义一个顶层方法
fun <T> request(callable: Callable<T>): T {
    return callable.call()
}