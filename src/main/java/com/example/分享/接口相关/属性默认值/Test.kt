package com.example.test.分享.接口相关.属性默认值

interface ITest {
    var tag: String
    val _tag: String
}
class Test(override var tag: String, override val _tag: String) : ITest