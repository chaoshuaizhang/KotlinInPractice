package com.example.kotlininpractice.daily.inaction

import com.example.kotlininpractice.daily.inaction.TestTmp.MyClazz2

class TestDTO {
    var sku: String? = null
    var orderNo: String? = null
    fun aaa(){

    }
}

fun main() {
    // testTo()
    // testRegex()
    // tripleMask()
    val impl = MyImpl("", "")
    impl.tag = ""
    TestTmp().A5()
//    val ss = Test4_2().CCCYYY()
//    testSealed(ss)
}

fun testTo() {
//    val clazz2 = MyClazz2()
    val item: Pair<String, Int> = "zero" to 0
    val (k, v) = "zero" to 0

    val map = mapOf("one" to 1, "two" to 2, item)

    map.forEach {
        // pair元素转换为了Map.Entry
    }
    val dto = TestDTO() bindSku "ANTA-Black-098" bindOrderNo "20210827666888"
    val dto2 = TestDTO().bindSku("").bindOrderNo("")

    // 解构生命
    for ((k, v) in map) {
    }
    val list = listOf(dto, dto2)
    val iterable: Iterable<IndexedValue<TestDTO>> = list.withIndex()
    for ((index, dto) in iterable) {
        println("$index - ${dto.orderNo}")
    }
}

// 实现一个给dto绑定sku的方法
infix fun TestDTO.bindSku(sku: String) = this.apply {
    this.sku = sku
}

// 实现一个给dto绑定订单号的方法
infix fun TestDTO.bindOrderNo(orderNo: String) = this.apply {
    this.orderNo = orderNo
}

fun testRegex() {
    println("""\.""".toRegex())
}

// 三重引号
fun tripleMask() {
    val tmp = """| //
                .|//
                .|/ \\"""
    println(tmp.trimMargin("."))
}

fun normalFunction() {
    val dto = TestDTO()
    if (dto.sku.isNullOrEmpty()) throw Exception()
    if (dto.orderNo.isNullOrEmpty()) throw Exception()
    println("dto is right")
}

// 使用局部函数进行优化
fun partialFunction_checkDTO() {
    val dto = TestDTO()
    fun checkNull(s: String?) {
        if (s.isNullOrEmpty()) throw Exception()
    }
    checkNull(dto.sku)
    checkNull(dto.orderNo)
    println("dto is right")
}

// 使用扩展函数内添加局部函数进行再次优化
fun check() {
    TestDTO().partialFunction_checkDTO()
}

fun TestDTO.partialFunction_checkDTO() {
    fun checkNull(s: String?) {
        if (s.isNullOrEmpty()) throw Exception()
    }
    checkNull(sku)
    checkNull(orderNo)
    println("dto is right")
}

// 使用扩展函数内添加局部函数进行再次优化
fun check2() {
    fun TestDTO.partialFunction_checkDTO() {
        fun checkNull(s: String?) {
            if (s.isNullOrEmpty()) throw Exception()
        }
        checkNull(sku)
        checkNull(orderNo)
        println("dto is right")
    }
    TestDTO().partialFunction_checkDTO()
}

interface MyInterface {
    var tag: String
    val unChangeTag: String

    companion object {
        val HTTP_CODE = 200
    }
}

class MyImpl(override var tag: String, override val unChangeTag: String)
    : MyInterface {
}

open class B0 {
    open fun b0fun() {
        println("b0")
    }
}

internal interface B1 {
    fun test_kotlin() {
        println("b1")
    }
}

internal interface B2 {
    fun test_kotlin() {
        println("b2")
    }
}

class B3 : B0(), B1, B2 {
    override fun b0fun() {
        super.b0fun()
    }

    override fun test_kotlin() {
        super<B1>.test_kotlin()
        super<B2>.test_kotlin()
    }
}

internal open class C0 {
    internal fun c0fun() {
        println("====+++++====")
    }
}
//class C1(c0: C0) {
//    fun fun1(c0:C0){}
//    internal fun fun2(c0:C0){}
//    private fun fun3(c0:C0){}
//}

public class ClassPublic {
    private var tag = ""

    inner class ClassProtected {
        private var tag = ""

        init {
            tag = ""
            this@ClassPublic.tag = ""
        }
    }

    fun aa() {
        ClassPrivate()
    }

    private class ClassPrivate {
        private val tag = ""

        init {

        }

    }

}

sealed class ClassSealed {
    private constructor()
}
class BBBYYY : ClassSealed() {
}



fun testSealed(e: ClassSealed) {
    when (e) {
        is BBBYYY -> { }
//        is Test4_2.CCCYYY -> { }
    }
}

