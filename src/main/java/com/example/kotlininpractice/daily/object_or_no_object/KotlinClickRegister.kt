package com.example.kotlininpractice.daily.object_or_no_object

import java.awt.event.ActionListener

class KotlinClickRegister {

    // 自定义kotlin代码中的listener
    fun addKotlinListener(listener: KotlinClickListener) {
        listener.click("kotlin click")
    }

    // 自定义java代码中的listener
    fun addJavaListener(listener: JavaClickListener) {
        listener.click("java click")
    }

    // 使用原生java代码中的listener
    fun addActionListener(listener: ActionListener) {

    }

}