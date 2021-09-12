package com.example.kotlininpractice.daily.inaction.运算符重载

fun main() {
    val wordDTO = WordDTO()
    val words: List<WordDTO> = wordDTO + WordDTO()
    words[0]
    val num = wordDTO.cheng(WordDTO())
    val num2 = wordDTO * WordDTO()
    wordDTO < WordDTO()
    wordDTO[0]
    val strategyManager = StrategyManager()
    strategyManager[ImgTransStrategy::class.java]
    val dto = WordDTO().apply { this.num = 5 }
    for (e in dto) {
        println(e.num)
    }
}

operator fun WordDTO.plus(other: WordDTO): List<WordDTO> {
    return listOf(this, other)
}

// 通过定义桥接方法来使用在java中已经定义过了的 乘方法
operator fun WordDTO.times(other: WordDTO): Int {
    return this.cheng(other)
}

operator fun WordDTO.unaryPlus(): WordDTO {
    return this.apply { num }
}

operator fun WordDTO.get(index: Int): WordDTO {
    return WordDTO()
}

operator fun WordDTO.iterator(): Iterator<WordDTO> {
    return object : Iterator<WordDTO> {
        var numTmp = 0
        override fun hasNext() = numTmp < num
        override fun next() = WordDTO().apply { num = numTmp++ }
    }
}

class StrategyManager {
    operator fun <T> get(type: Class<T>): IStrategy? {
        return when (type.typeName) {
            TextTransStrategy::class.java.typeName -> TextTransStrategy()
            ImgTransStrategy::class.java.typeName -> ImgTransStrategy()
            StreamTransStrategy::class.java.typeName -> StreamTransStrategy()
            else -> throw IllegalArgumentException()
        }
    }
}

interface IStrategy
class TextTransStrategy : IStrategy
class ImgTransStrategy : IStrategy
class StreamTransStrategy : IStrategy