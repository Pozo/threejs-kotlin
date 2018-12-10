@file:JsQualifier("THREE")

package three.core

@JsName("BufferGeometry")
open external class BufferGeometry {
    var attributes: dynamic

    fun rotateX(radians: Float)
    fun addAttribute(name: String, attribute: BufferAttribute): BufferGeometry
    fun clone(): BufferGeometry
}

