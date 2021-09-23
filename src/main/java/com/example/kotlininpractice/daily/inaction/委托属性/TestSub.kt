package com.example.kotlininpractice.daily.inaction.委托属性

import kotlin.random.Random
import kotlin.reflect.KProperty

class VoiceCheck {
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): Boolean {
        property.name
        val voiceCount = Random.nextInt(5) - 3
        return voiceCount > 0
    }
}

val voicePermission: Boolean by VoiceCheck()

fun main() {
    println(voicePermission)
}