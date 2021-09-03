package com.example.kotlininpractice.inaction2

class LimitSetGetVisit {
    private val name: String
        // get无需设置可见性修饰符，因为get方法的可见性要和属性本身一致，比如现在是private，则get就是private
        // 属性改为public，get就在自动变成了public
        // 所以无需设置
        get() {
            return ""
        }
    // set方法可以通过设置可见性修饰符来限定访问可见性
    var age = 25
        private set
        // 由上面可知，【age的get方法是public的，因为age本身就是public的】
}
// 也就是说，我们可以自定义写操作的访问权限（不能大于属性本身的权限），而读操作权限，和属性本身保持一致

fun main() {
    val dto = LimitSetGetVisit()
}