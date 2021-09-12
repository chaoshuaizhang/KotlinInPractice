package com.example.kotlininpractice.daily.inaction.可空类型的扩展

import java.lang.NullPointerException
import kotlin.random.Random

fun main() {
    val nullElementClass = NullElementClass()
    nullElementClass.test()
    val s: String? = null
    NullElementClass.wrapperString("")
}

class NullElementClass {

    fun test() {
        val s: String? = null
        println(s.addRandom())
    }

    private fun String?.addRandom(): Double {
        val l = this?.length ?: 0
        return l + Random.nextDouble()
    }

    companion object {
        private fun Any.doSth() {
            this.toString()
        }

        fun <T : Any> wrapper(t: T) {
            t.doSth()
        }

        fun wrapperString(t: String) {
            println(t.hashCode())
        }
    }

}