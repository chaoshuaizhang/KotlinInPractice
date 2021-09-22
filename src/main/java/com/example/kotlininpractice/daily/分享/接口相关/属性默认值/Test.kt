package com.example.kotlininpractice.daily.分享.接口相关.属性默认值

interface ITest {
    val tag: String
}
class Test(override val tag: String) : ITest{}