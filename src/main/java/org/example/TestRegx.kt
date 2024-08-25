package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.io.IOException
import java.net.URI
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes

fun divBy100(n: Int): Int {
    return ((n * 0x51EB851FL) ushr 37).toInt()
}

fun main() {

    Files.walk(Path.of("./src")).sorted(Comparator.reverseOrder()).forEach {
        println(it.toString())
    }

    Files.walkFileTree(Path.of("./src"), object : FileVisitor<Path> {
        override fun preVisitDirectory(dir: Path?, attrs: BasicFileAttributes?): FileVisitResult {
            TODO("Not yet implemented")
        }

        override fun visitFile(file: Path?, attrs: BasicFileAttributes?): FileVisitResult {
            TODO("Not yet implemented")
        }

        override fun visitFileFailed(file: Path?, exc: IOException?): FileVisitResult {
            TODO("Not yet implemented")
        }

        override fun postVisitDirectory(dir: Path?, exc: IOException?): FileVisitResult {
            TODO("Not yet implemented")
        }
    })

    println(VersionUtils.compareVersion("2.0.0", "1.0.0-alpha.1.1"))
    println(VersionUtils.compareVersion("2.0.0.1", "2.0.0"))
    println(VersionUtils.compareVersion("2.0.0", "2.0.1-alpha.a"))

    val regex = "^[0-9]+(-[a-zA-Z0-9]+)?\\.[0-9]+(-[a-zA-Z0-9]+)?\\.[0-9]+(-[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*)?\$".toRegex()

    val validVersions = listOf("1.2.3", "1.0.1-alpha", "0.0.1-alpha.22")
    val invalidVersions = listOf("1.1.1.1", "1..1", "1.1.-alpha")

    validVersions.forEach {
        if (regex.matches(it)) {
            println("$it is valid.")
        } else {
            println("$it is invalid.")
        }
    }

    invalidVersions.forEach {
        if (regex.matches(it)) {
            println("$it is valid.")
        } else {
            println("$it is invalid.")
        }
    }
}


suspend fun maain() {
    val channel = Channel<Int>(0)

//    val consumer = GlobalScope.launch {
//        while (true) {
//            delay(10010)
//            val element = channel.receive()
//            println(element)
//        }
//    }
//
//    val consumer2 = GlobalScope.launch {
//        while (true) {
//            delay(10000)
//            channel
//                .receiveAsFlow()
//                .filter { it > 5 }
//                .collect {
//                    println("consumer2: $it")
//                }
//        }
//    }

    val producer = GlobalScope.launch {
        var i = 0
        while (i < 10) {
            //delay(1000)
            channel.send(i++)
            println("                                        $i")
        }
    }

    producer.join()
//    consumer.join()
//    consumer2.join()
}


val scope = CoroutineScope(Dispatchers.IO)
suspend fun maisn() = runBlocking {
    val channel = Channel<Int>(100)
    GlobalScope.launch {
        for (i in 0..5) {
            delay(100)
            channel.send(i)
        }
    }
    GlobalScope.launch {
        channel.receiveAsFlow().collect {
            println("all: $it")
        }
    }
    GlobalScope.launch {
        channel.receiveAsFlow()
            .filter { it > 3 }
            .collect {
                println("filter: $it")
            }
    }
    delay(2_000)
    Unit
}

fun main2() {

    println("1".toByteArray().joinToString("") { "%02d".format(it) })

    // 假设这个是文件路径
    val path: Path = Paths.get("/example/path/to/your/file.txt")
    URI.create("/example/path/to/your/file.txt")
    // 获取文件名（带后缀）
    val fileName: String = path.fileName.toString()

    // 打印文件名，带文件后缀
    println("File name: $fileName")

//    for (number in 198..300) {
//        val result = divBy100(number)
//        println("$number / 100 = $result")
//    }
}


fun main1() {


    testrun()

    val inputList = mutableListOf(
        "clascdscd_2024-08-15_de1.log",
        "classasup_2024-08-15_09nd.log",
        "aclassup_2024-08-15_1sw.log"
    )

    val target = mutableListOf<String>()
    for (i in 0 until 10000) target.addAll(inputList)
    val currentTimeMillis = System.currentTimeMillis()
//    main0(target) // 30
//    main1(target) // 19
//    main2(target) // 18
//    main3(target) // 25
    println(System.currentTimeMillis() - currentTimeMillis)
    Thread.sleep(3_000)
}

fun main0(inputList: MutableList<String>) {
    val regex = Regex("""_([^_]+)_""")

    // 处理每个输入字符串并提取两个下划线之间的内容
    inputList.forEach { input ->
        val matches = regex.findAll(input)
        matches.forEach { matchResult ->
            matchResult.groupValues[1]
            // println("Extracted content from '$input': ${matchResult.groupValues[1]}")
        }
    }
}

fun main1(inputList: MutableList<String>) {
    val regex = Regex("""_(.*?)_""")

    // 处理每个输入字符串并提取两个下划线之间的内容
    inputList.forEach { input ->
        val matchResult = regex.find(input)
        if (matchResult != null) {
            var orNull = matchResult.groupValues.getOrNull(1)
            println("Extracted content from '$input': ${orNull}")
        } else {
//            println("No content found in '$input'")
        }
    }
}

fun main2(inputList: MutableList<String>) {
    // 定义正则表达式模式
    val regex = Regex("""_([0-9]{4}-[0-9]{2}-[0-9]{2})_""")

    // 处理每个输入字符串并提取日期
    inputList.forEach { input ->
        val matchResult = regex.find(input)
        val date = matchResult?.groups?.get(1)?.value
        if (date != null) {
            println("Extracted date from '$input': $date")
        } else {
//            println("No valid date found in '$input'")
        }
    }
}

fun main3(inputList: MutableList<String>) {
    // 定义正则表达式模式
    val regex = Regex("""[a-zA-Z0-9]+_(\d{4}-\d{2}-\d{2})_[a-zA-Z0-9]+\.log""")

    // 处理每个输入字符串并提取日期
    inputList.forEach { input ->
        val matchResult = regex.find(input)
        val date = matchResult?.groups?.get(1)?.value
        if (date != null) {
            println("Extracted date from '$input': $date")
        } else {
//            println("No valid date found in '$input'")
        }
    }
}

fun testrun() = runBlocking {
    val mySafeFlow = MySafeFlow<Int>()
    val myFlowCollector = MyFlowCollector<Int>()
    mySafeFlow.bindCollector(myFlowCollector)
    myFlowCollector.apply {
        emit(1)
        delay(200)
        emit(2)
        delay(200)
        emit(3)
        delay(200)
        emit(4)
        delay(200)
        emit(5)
    }

    flow {
        emit(1)
    }.collect {

    }

    mySafeFlow.collect {
        println(it)
    }

}


class MySafeFlow<T> : AbstractFlow<T>() {

    private var collector: FlowCollector<T>? = null

    fun bindCollector(collector: FlowCollector<T>) {
        this.collector = collector
    }

    override suspend fun collectSafely(collector: FlowCollector<T>) {
        println(Thread.currentThread().name)
        collector.emit(1 as T)
    }

}

class MyFlowCollector<T> : FlowCollector<T> {
    override suspend fun emit(value: T) {

    }
}