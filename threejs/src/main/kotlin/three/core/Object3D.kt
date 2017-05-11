@file:JsQualifier("THREE")

package three.core

import three.math.Euler
import three.math.Vector3

@JsName("Object3D")
external open class Object3D {
    var position: Vector3
    var up: Vector3
    var rotation: Euler
    var castShadow: Boolean
    var receiveShadow: Boolean

    fun lookAt(vector: Vector3)
    fun add(pointLight: Object3D)
    fun rotateOnAxis(axis: Vector3, angle: Double)
}