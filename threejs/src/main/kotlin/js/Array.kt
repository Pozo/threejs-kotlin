package js

@JsName("Array")
external class JsArray<T> {
    fun push(item: T)
    fun pop(): T
    fun get(index: Int): T
    fun set(index: Int, value: T)

    val length: Int
    fun indexOf(elemnt: T): Int
    fun splice(indexOf: Int, i: Int)
}
