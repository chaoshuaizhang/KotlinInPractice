package com.example.kotlininpractice.t

import kotlin.jvm.internal.Intrinsics

fun main() {
    val dog1 = Dog()
    dog1.eat()
    dog1.doSth()
    // 注意加不加Animal限定，执行的结果是不一样的
    val dog2: Animal = Dog()
    dog2.eat()
    dog2.doSth()

    val list = listOf<Int>(1,2,3,4,5)
    list.takeWhile {
        it == 3
    }.firstOrNull()

    // eat是扩展方法，doSth是类自己的方法，上述的执行结果可以看出来两者是有区别的
}

// 下边是扩展方法编译后的代码
//fun eat(`$this$eat`: Animal) {
//    Intrinsics.checkParameterIsNotNull(`$this$eat`, "\$this\$eat")
//    val var1 = "kong qi"
//    val var2 = false
//    println(var1)
//}
//
//fun eat(`$this$eat`: Dog) {
//    Intrinsics.checkParameterIsNotNull(`$this$eat`, "\$this\$eat")
//    val var1 = "feet"
//    val var2 = false
//    println(var1)
//}

fun Animal.eat() {
    println("kong qi")
}

fun Dog.eat() {
    println("feet")
}

class KotlinPS {
}