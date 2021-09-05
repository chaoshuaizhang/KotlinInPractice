package com.example.kotlininpractice

fun main() {
    val ti = TestInvoke()
    ti()
    val tiFun = TestInvokeFun()
    tiFun {

    }

    (object : ITestInvoke {

    }).invoke("================")

    addInvokeListener((object : ITestInvoke {

    }))

    (object : ITestInvoke {}).invoke("")

    ITestInvokeObject()

    ITestInvokeObjectFun {

    }


}

class TestInvoke {

    operator fun invoke() {
        println("-- invoke --")
    }

}

class TestInvokeFun {

    operator fun invoke(cb: () -> Unit) {
        cb()
    }

}

fun addInvokeListener(listener: ITestInvoke) {
    listener.invoke("test tag")
}

interface ITestInvoke {

    operator fun invoke(string: String): Int {
        println("-- static invoke --   $string")
        return 1
    }

}

interface ITestInvokeObject {

    companion object {
        operator fun invoke() {
            println("-- static invoke --")
        }
    }
}

interface ITestInvokeObjectFun {

    companion object {
        operator fun invoke(cb: (Boolean) -> Unit) {
            cb(true)
        }
    }
}

