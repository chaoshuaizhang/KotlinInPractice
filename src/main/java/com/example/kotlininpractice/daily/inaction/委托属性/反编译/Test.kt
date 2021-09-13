package com.example.kotlininpractice.daily.inaction.委托属性.反编译

import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// 默认的方式（等价于实现ReadWriteProperty接口，ReadWriteProperty接口也只有两个方法：setValue和getValue）
class MyDelegate {
    operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
        return "MMKV的值"
    }
    operator fun setValue(nothing: Nothing?, property: KProperty<*>, s: String) {
        val setMMKV = "设置MMKV的值"
    }
}

// 代理对象定义在外部
// var params: String by MyDelegate()
// 拆解上述代码
private val delegate = MyDelegate()
var params: String by delegate


// 代理对象定义在类中
//class MyDelegate:ReadWriteProperty<MyMain, String> {
//    override operator fun getValue(nothing: MyMain, property: KProperty<*>): String {
//        return "MMKV的值"
//    }
//
//    override operator fun setValue(nothing: MyMain, property: KProperty<*>, s: String) {
//        val setMMKV = "设置MMKV的值"
//    }
//}
//
//class MyMain {
//    // var params: String by MyDelegate()
//    // 拆解上述代码
//    private val delegate: ReadWriteProperty<MyMain, String> = MyDelegate()
//    var params: String by delegate
//}


//class MyDelegate:ReadWriteProperty<Nothing?, String> {
//    override operator fun getValue(nothing: Nothing?, property: KProperty<*>): String {
//        return "MMKV的值"
//    }
//
//    override operator fun setValue(nothing: Nothing?, property: KProperty<*>, s: String) {
//        val setMMKV = "设置MMKV的值"
//    }
//}
//
//// 代理对象定义在外部
//// var params: String by MyDelegate()
//// 拆解上述代码
//private val delegate: ReadWriteProperty<Nothing?, String> = MyDelegate()
//var params: String by delegate