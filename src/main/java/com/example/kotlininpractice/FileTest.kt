package com.example.kotlininpractice

import com.xxl.tool.excel.ExcelTool
import com.xxl.tool.excel.annotation.ExcelField
import com.xxl.tool.excel.annotation.ExcelSheet
import org.apache.poi.hssf.util.HSSFColor
import java.io.File
import java.util.*
import java.util.regex.Pattern


fun <T> MutableList<T>?.removeRange(start: Int, end: Int) {
    if (maxOf(start, end) < this?.size ?: 0)
        for (i in start..end) this?.removeAt(start)
}

object StringUtils {

    val s = "vnjfnvjfn"

    fun getLastPathSegment(content: String?): String {
        if (content == null || content.length == 0) {
            return ""
        }
        val segments = content.split("/").toTypedArray()
        return if (segments.size > 0) {
            segments[segments.size - 1]
        } else ""
    }
}


fun main() {

    val spl = "vnjfnvjfn"
    var format = String.format(spl, "11223")

    for (i in 0..3) {
        println("+++++++++++++++++   $i")
    }

    val list = mutableListOf<Int>(0, 1, 2, 3, 4, 5)
    list.removeRange(1, 1)
    println("------------------------")
    list.forEach {
        print("$it  ")
    }
    println()

    val syt = "vbhfdbv/vnfv/vfnvhbfh/"
    var split = syt.split("/")
    var lastPathSegment = StringUtils.getLastPathSegment(syt)

    val s = "<string name=\"camera_translation\">&lt;b>Camera&lt;/b> Translation</string>"
    val reg_type = "\\=\"(.*?)\">"
    var matcher1 = Pattern.compile(reg_type).matcher(s)
    if (matcher1.find()) println(matcher1.group(1))
    val reg_value = "\\>(.*?)</"
    var matcher2 = Pattern.compile(reg_value).matcher(s)
    if (matcher2.find()) println(matcher2.group(1))


    generateXXXLangByStringXml()
    StringUtils.s
}

fun dd() {
    val keys = arrayListOf<String>(
            "login_login", "login_facebook", "login_google", "login_login_failed", "login_logout", "login_privacy", "login_terms", "login_policy_desc1", "login_policy_policy", "login_policy_desc2"
    )
    val tmp = "<string name=\"%s\">%s</string>"
    val file = File("C:\\Users\\zhangcs\\Desktop\\tmp.txt")
    file.readLines().forEachIndexed { i, s ->
        val s = String.format(tmp, keys[i], s)
        println(s)
    }
}

fun generateXXXLangByStringXml() {

    val enFilePath = "F:\\strings-en.xml"
    val thFilePath = "F:\\strings-th.xml"

    var count = 0
    File(enFilePath).readLines().forEach {
        if (it.trim().isNotBlank() and it.trim().isNotEmpty() and (it.trim().startsWith("<string") or it.trim().startsWith("<item"))) {
            count++
        }
    }
    println(count)

    val thMap = generateStringMap(thFilePath)
    val enMap = generateStringMap(enFilePath)
    val list = mutableListOf<ThToEng>()
    enMap.iterator().forEach {
        val thToEng = ThToEng(it.key, it.value, thMap[it.key])
        list.add(thToEng)
    }

    val listArray = mutableListOf<ThToEng>()
    var enArrayMap = generateStringArrayMap(enFilePath)
    var thArrayMap = generateStringArrayMap(thFilePath)
    enArrayMap.iterator().forEach {
        val type = it.key
        val thItems = thArrayMap[type]
        it.value?.forEachIndexed { index, en ->
            val thToEng = ThToEng(type, en, thItems?.get(index))
            listArray.add(thToEng)
        }

//        if (thArrayMap.containsKey(type)) {
//            val thItems = thArrayMap[type] ?: return@forEach
//            it.value?.forEachIndexed { index, en ->
//                val thToEng = ThToEng(type, en, thItems[index])
//                listArray.add(thToEng)
//            }
//        }
    }
    println((list.size + listArray.size))
    when {
        list.isEmpty() -> ExcelTool.exportToFile(listOf(listArray), "F:\\aaa.xlsx")
        listArray.isEmpty() -> ExcelTool.exportToFile(listOf(list), "F:\\aaa.xlsx")
        else -> ExcelTool.exportToFile(listOf(list, listArray), "F:\\aaa.xlsx")
    }
}

fun generateStringMap(filePath: String): LinkedHashMap<String, String> {
    val thFile = File(filePath)
    val map = linkedMapOf<String, String>()
    thFile.readLines().forEach { s ->
        val it = s.trim()
        //  or it.startsWith("<string-array")
        if (it.startsWith("<string ")) {
            val reg_type = "\\=\"(.*?)\">"
            val pattern = Pattern.compile(reg_type)
            val matcher = pattern.matcher(it)
            if (matcher.find()) {
                val type = matcher.group(1)
                val reg_value = "\\\">(.*?)</string>"
                val pattern = Pattern.compile(reg_value)
                val matcher = pattern.matcher(it)
                if (matcher.find()) {
                    val value = matcher.group(1)
                    map[type] = value.replace("&lt;", "<")
                }
            }
        }
    }
    return map
}

fun generateStringArrayMap(filePath: String): LinkedHashMap<String, MutableList<String>?> {
    val thFile = File(filePath)
    val map = linkedMapOf<String, MutableList<String>?>()
    var list: MutableList<String>? = null
    var typeTmp: String? = ""
    thFile.readLines().forEach { s ->
        val it = s.trim()
        //  or it.startsWith("<string-array")
        if (it.startsWith("<string-array ")) {
            // start
            val reg_type = "\\=\"(.*?)\">"
            val pattern = Pattern.compile(reg_type)
            val matcher = pattern.matcher(it)
            if (matcher.find()) {
                list = mutableListOf()
                typeTmp = matcher.group(1)
                typeTmp?.let {
                    map[it] = list
                }
            }

        } else if (it.startsWith("</string-array")) {
            // stop
            list = null
            typeTmp = null
        } else if (it.startsWith("<item>")) {
            val reg_type = "\\<item>(.*?)</item>"
            val pattern = Pattern.compile(reg_type)
            val matcher = pattern.matcher(it)
            if (matcher.find()) {
                val itemValue = matcher.group(1)
                typeTmp?.let {
                    map[it]?.add(itemValue.replace("&lt;", "<"))
                }
            }
        }
    }
    return map
}

@ExcelSheet(name = "英->泰对照", headColor = HSSFColor.HSSFColorPredefined.LIGHT_BLUE)
data class ThToEng(@ExcelField(name = "属性名") val name: String, @ExcelField(name = "英文") val en: String?, @ExcelField(name = "泰语") val th: String?)