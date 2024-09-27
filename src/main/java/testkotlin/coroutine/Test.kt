package testkotlin.coroutine

import java.nio.ByteBuffer
import kotlin.math.abs

class Test {
}

fun main() {
    val b100 = 0b01111111.toByte()
    val b58 = 0b11111111.toByte()
    log(b100.toInt())
    val buffer = ByteBuffer.allocate(2).put(b100).put(b58)
    val sampleAbs = abs(buffer.getShort(0).toInt())
    val sampleAbs2 = abs((buffer.get(0).toInt() and 0xFF).shl(8)) or abs((buffer.get(0 + 1).toInt() and 0xFF))
    log("$sampleAbs - $sampleAbs2")
}