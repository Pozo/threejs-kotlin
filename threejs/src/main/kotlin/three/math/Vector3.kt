@file:JsQualifier("THREE")

package three.math

@JsName("Vector3")
external class Vector3 {
    constructor()
    constructor(x: Float, y: Float, z: Float)

    var x: Double
    var y: Double
    var z: Double

    fun set(x: Float, y: Float, z: Float): Vector3
    fun length(): Double
    fun normalize()
    fun copy(point: Vector3): Vector3
    fun add(vector: Vector3)
    fun divideScalar(scalar: Float): Vector3
    fun floor(): Vector3
    fun multiplyScalar(scalar: Float): Vector3
    fun addScalar(scalar: Float): Vector3
}