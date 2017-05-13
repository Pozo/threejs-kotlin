package js

@JsName("Array")
external class JsArray<T> {
    fun push(item: T)
    fun pop(): T
    fun get(index: Int): T
    fun set(index: Int, value: T)
}
