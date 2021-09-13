package com.example.kotlininpractice.daily.inaction.委托属性

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/*
* 通过以下1.0到，来推出属性委托是如何工作的
* */
open class WordChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removeChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

//----------------------------------------1.0-----------------------------------
/*
* 1.0 缺点是set方法里边的大多数代码都是重复的
* */
class Word(trans: String, desc: String) : WordChangeAware() {
    var trans = trans
        set(value) {
            val oldValue = field
            field = value
            changeSupport.firePropertyChange("trans", oldValue, value)
        }
    var desc = desc
        set(value) {
            val oldValue = field
            field = value
            changeSupport.firePropertyChange("desc", oldValue, value)
        }
}
//----------------------------------------2.0-----------------------------------

/*
* 2.0 这里自定义了一个ObservableProperty，就可以称为属性委托：他存储了实际业务中的一个属性值
* */
class ObservableProperty(val propertyName: String, var propertyValue: String, val changeSupport: PropertyChangeSupport) {
    fun getValue() = propertyValue
    fun setValue(value: String) {
        val oldValue = propertyValue
        propertyValue = value
        changeSupport.firePropertyChange(propertyName, oldValue, propertyValue)
    }
}

class Word2(trans: String, desc: String) : WordChangeAware() {
    private val _trans = ObservableProperty("trans", trans, changeSupport)
    var trans: String
        get() = _trans.propertyValue
        set(value) {
            _trans.setValue(value)
        }

    private val _desc = ObservableProperty("desc", trans, changeSupport)
    var desc: String
        get() = _desc.propertyValue
        set(value) {
            _desc.setValue(value)
        }
}
//----------------------------------------2.0-----------------------------------

//----------------------------------------3.0-----------------------------------
class ObservableProperty2(var propertyValue: String, val changeSupport: PropertyChangeSupport) {
    operator fun getValue(dto: Welcome, prop: KProperty<*>) = propertyValue
    operator fun setValue(dto: Welcome, prop: KProperty<*>, value: String) {
        val oldValue = propertyValue
        propertyValue = value
        changeSupport.firePropertyChange(prop.name, oldValue, value)
    }
}

class Welcome(trans: String, val desc: String) : WordChangeAware() {
    var trans: String by ObservableProperty2(trans, changeSupport)
}
//----------------------------------------3.0-----------------------------------


//----------------------------------------4.0-----------------------------------
class Person(name: String) : WordChangeAware() {
    private val observer = { prop: KProperty<*>, oldValue: String, newValue: String ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
    var name: String by Delegates.observable(name, observer)
}
//----------------------------------------4.0-----------------------------------

class DelegatePerson {
    private var _name = ""
    operator fun getValue(person2: Person2, property: KProperty<*>): String {
        return _name
    }

    operator fun setValue(person2: Person2, property: KProperty<*>, s: String) {
        _name = s
    }
}

class Person2 {
    var name: String by DelegatePerson()
}

class Person3 {
    private val attrs = hashMapOf<String, String>()
    fun setAttrs(attrName: String, value: String) {
        attrs[attrName] = value
    }
    var key: String by attrs
}

//----------------------------------------测试方法-----------------------------------
fun main() {
    // -------------------------------1.0
    val hello = Word("你好", "问候的一种方式")
    hello.addChangeListener(PropertyChangeListener {
        println("""${it.propertyName}
            |${it.oldValue}
            |${it.newValue}
        """.trimMargin().removePrefix("|"))
    })
    hello.trans = "莎啦啦"
    hello.trans = "莎啦啦啦啦啦啦啦啦"
    // -------------------------------1.0

    // -------------------------------2.0
    val world = Word2("世界", "世界的英文")
    world.addChangeListener(PropertyChangeListener {
        println("""${it.propertyName}
            |${it.oldValue}
            |${it.newValue}
        """.trimMargin().removePrefix("|"))
    })
    world.trans = "全世界都需要你"
    // -------------------------------2.0

    // -------------------------------3.0
    // -------------------------------3.0

    // -------------------------------4.0
    val p = Person("zcs")
    p.addChangeListener(PropertyChangeListener {
        println("""${it.propertyName}
            |${it.oldValue}
            |${it.newValue}
        """.trimMargin().removePrefix("|"))
    })
    p.name = "zph"
    // -------------------------------4.0

    val person2 = Person2()
    person2.name = "name222"
    println(person2.name)
    person2.name = "name333"
    println(person2.name)

    val person3 = Person3()
    person3.setAttrs("key","哈哈哈")
    println(person3.key)
    person3.setAttrs("key33333","哈哈哈33333")
    println(person3.key)
//    person3.key = "kkkkk"
//    person3.key

}