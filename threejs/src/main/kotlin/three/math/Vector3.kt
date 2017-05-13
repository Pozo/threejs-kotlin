@file:JsQualifier("THREE")

package three.math

@JsName("Vector3")
external class Vector3 {
    constructor(x: Double, y: Double, z: Double)

    var x: Double
    var y: Double
    var z: Double

    fun set(x: Double, y: Double, z: Double)
    fun length(): Double
}