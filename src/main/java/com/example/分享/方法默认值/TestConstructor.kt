package com.example.test.分享.方法默认值

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


open class MyDTO {
    companion object {
        val instance = MyDTO()
    }
}
val tag: String by lazy {
    ""
}
class MyDTO4(val name: String = "zcs", age: Int = 15, var sex: String, referenceType: MyDTO = MyDTO.instance) {

}

fun main() {
    val dto = MyDTO4(sex = "boy")
//    var i = 1
//    println(i-- > 0)
//    println(Random.nextInt(0))
    val s = ""
//    println(s[-1])
//    println(Calendar.getInstance().timeInMillis)
//    println(System.currentTimeMillis())
//    println(Instant.now())
//    OffsetDateTime.now()

//    println(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").apply {
//        timeZone = TimeZone.getTimeZone("GMT")
//    }.parse("2021-10-12 19:09:20").time)
//
////    println(SimpleDateFormat().format(Date()))
//    val ss = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").apply {
//        timeZone = java.util.TimeZone.getTimeZone("UTC")
//    }
//    println(ss.parse(SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Date())))
////    println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond())

//    val utcFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//    println(utcFormat.parse("2021-10-12 11:29:49").time)
//    utcFormat.timeZone = TimeZone.getTimeZone("UTC")
//    val date = utcFormat.parse("2021-10-12 11:29:49")
//    println(date.time)
//    // val pstFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//    utcFormat.timeZone = TimeZone.getTimeZone(ZoneId.systemDefault())
//    println(utcFormat.parse(utcFormat.format(date)).time)

    val timeStr = "2021-10-12 11:29:49.999"
    var installTime = 0L
    // 1634038189999
    // 1634038189999
    // 1634009389999
    // 1634009389999
    try {
        installTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").apply {
            timeZone = java.util.TimeZone.getTimeZone("UTC")
        }.parse(timeStr)?.time ?: 0
    } catch (e: Exception) { }
    println(installTime)
    println(LocalDateTime.now(ZoneId.of("UTC")))
    println(LocalDateTime.now(ZoneId.systemDefault()))

//    println("channel=organic&imei=12345&oaid=123456&version=1.0.0&sign=123456".md5())
    println("==================")
    println(Date().toString())

    runBlocking {
        flow<Int> {
            emit(99 / 1)
        }
            .catch {
                println("-------   ${this.toString()}")
            }.collect {
                println("+++++++   $it")
            }
    }


}

fun String.md5(): String {
    val md = MessageDigest.getInstance("md5")
    val data = md.digest(toByteArray())
    return BigInteger(1, data).toString(16).padStart(32, 'a')
}

fun mDate(){
    // 设置当前是UTC
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    val date: Date = Date()
    println(date)
    // 恢复当前
    TimeZone.setDefault(null);
    println(Date())
    // ============================
    val installTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }.parse("UTC时间")?.time ?: 0
}