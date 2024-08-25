package org.example.test

import com.epoch.classup.jsinterface.proto.source.WebCameraProto.CameraInfo
import java.io.ByteArrayOutputStream
import java.util.Base64

fun main() {


    val byte = 16.toByte()
    println(byte)

    val list = mutableListOf("1", "2", "3", "4", "5", "6")
    val iterator = list.iterator()
    iterator.forEach {
        if (it == "3") iterator.remove()
    }
    list.iterator().forEach {
        println(it)
    }
    val str = "8"
    val bytes = str.encodeToByteArray()
    println(bytes.size)
    println(bytes.get(0))
    println(bytes.get(1))
    println(bytes.get(2))
    ByteArray(3).apply {
        this.set(0, bytes[0])
        this.set(1, bytes[1])
        this.set(2, bytes[2])
    }.also {
        println(String(it))
    }
//    val cameraInfo = CameraInfo.newBuilder()
//        .setLog("qwertyuioiuytrtyuiuiuytyui")
//        .setName("testtesttest")
//        .build()
//    val outputStream = ByteArrayOutputStream()
//    cameraInfo.writeTo(outputStream)
//    val str1 = outputStream.toString()
//    println("stream to string: " + str1.length)
//    val byteArray = outputStream.toByteArray()
//    val str2 = Base64.getEncoder().encodeToString(byteArray)
//    println("stream to array to base64: " + str2.length)
//
//    println("-----------------")
//
//    println(str1)
//    println(String(str1.toByteArray()))
//    println(String(Base64.getDecoder().decode(str2)))
//    val aaaImpl = AAAImpl()
//    aaaImpl.write()
}

interface AAA {
    fun write(msg: String? = "default")
}

class AAAImpl : AAA {
    override fun write(msg: String?) {
        println(msg)
    }

}