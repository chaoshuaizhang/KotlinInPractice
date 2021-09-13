package com.example.kotlininpractice.daily.inaction.测试javakotlin注册接口的区别

class MyKotlinClass {
    fun addKListener(listener: MyKotlinListener) {}
    fun addJListener(listener: MyJavaListener) {}

    fun addSingleCLickListener(listener: MySingleClickKListener) {}
    fun addMultiCLickListener(listener: MyMultiClickKListener) {}

    fun addSingleCLickListener(listener: MySingleClickJListener) {}
    fun addMultiCLickListener(listener: MyMultiClickJListener) {}
}

interface MyKotlinListener {}
interface MySingleClickKListener {
    fun click()
}
interface MyMultiClickKListener {
    fun click1()
    fun click2()
}

fun test() {
    // kotlin类
    val kotlinClass = MyKotlinClass()
    // 注册kotlin接口
    kotlinClass.addKListener(object : MyKotlinListener {})
    // 注册java接口
    kotlinClass.addJListener(MyJavaListener { TODO("Not yet implemented") })
    kotlinClass.addSingleCLickListener(object : MySingleClickKListener {
        override fun click() {
            TODO("Not yet implemented")
        }
    })
    kotlinClass.addMultiCLickListener(object : MyMultiClickKListener {
        override fun click1() {
            TODO("Not yet implemented")
        }

        override fun click2() {
            TODO("Not yet implemented")
        }
    })
    // 函数式接口支持Lambda转换
    kotlinClass.addSingleCLickListener(MySingleClickJListener { TODO("Not yet implemented") })
    // 非函数式接口不支持
    kotlinClass.addMultiCLickListener(object : MyMultiClickJListener {
        override fun click2() {
            TODO("Not yet implemented")
        }

        override fun click1() {
            TODO("Not yet implemented")
        }
    })


    // java类
    val javaClass = MyJavaClass()
    // 添加java接口
    javaClass.addJListener(object : MyJavaListener {
        override fun click() {
            val l: MyJavaListener = this
        }
    })
    // 添加kotlin接口
    javaClass.addKListener(object : MyKotlinListener {})
    // SAM转换
    val kotlinClass2 = MyKotlinClass()
    kotlinClass2.addJListener(MyJavaListener {
        val l: Int = 0
    })

    val listener = Runnable { }

}