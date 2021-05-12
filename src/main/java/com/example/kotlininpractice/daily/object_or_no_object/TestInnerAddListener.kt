package com.example.kotlininpractice.daily.object_or_no_object

import java.awt.Button
import java.awt.event.ActionListener

fun main() {

    // 定义kotlin代码对象
    val clickRegister = KotlinClickRegister()
    // 添加 kotlin 接口
    clickRegister.addKotlinListener(object : KotlinClickListener {
        override fun click(s: String) {

        }
    })
    // 添加 java 接口
    clickRegister.addJavaListener(JavaClickListener { })

    clickRegister.addActionListener(ActionListener {})
    // 上下两个貌似没有任何区别，但是写法为啥就不一样
    Button().addActionListener {}
    Button().addActionListener(ActionListener { })

    // 定义java代码对象
    val javaClickRegister = JavaClickRegister()
    javaClickRegister.addActionListener { }
    // TODO 这下看出区别了：java类注册java接口，可以直接写成{}，这是java8 lambda的原因，与kotlin无关
    javaClickRegister.addJavaListener { }
    // 添加kotlin接口，还是如此复杂，必须要用object
    javaClickRegister.addKotlinListener(object : KotlinClickListener {
        override fun click(s: String) {

        }
    })

    /*
    * 总结：
    * java注册java，可以很简单，直接{]
    * java注册kotlin：使用 object
    * kotlin注册kotlin：使用 object
    * kotlin注册java：不用写object，但是需要指明接口名
    *
    * TODO 注意：需要看下字节码，看下什么原因，为什么kotlin中间要加一个object: XXX，为什么不能像java8那么简洁？
    * */

}