package com.example.test.分享.中缀调用

import com.example.test.分享.Word


// 实现一个给Word绑定单词的方法
infix fun Word.bindWord(word: String) = this.apply {
    this.word = word
}
// 实现一个给word绑定订单号的方法
infix fun Word.bindTrans(trans: String) = this.apply {
    this.trans = trans
}
// 使用（这个例子只是引出中缀的作用，实际也不会这么写）
val word = Word() bindWord "hello" bindTrans "你好"
// 改成这样就类似Builder建造者了
val word2 = Word().bindWord("").bindTrans("")