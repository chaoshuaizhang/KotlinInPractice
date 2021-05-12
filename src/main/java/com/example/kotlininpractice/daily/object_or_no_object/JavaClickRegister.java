package com.example.kotlininpractice.daily.object_or_no_object;

import java.awt.event.ActionListener;

public class JavaClickRegister {

    // 自定义kotlin代码中的listener
    void addKotlinListener(KotlinClickListener listener) {
        listener.click("kotlin click");
    }

    // 自定义java代码中的listener
    void addJavaListener(JavaClickListener listener) {
        listener.click("java click");
        JavaClickRegister r = new JavaClickRegister();
    }

    // 使用原生java代码中的listener
    void addActionListener(ActionListener listener) {

    }

}
