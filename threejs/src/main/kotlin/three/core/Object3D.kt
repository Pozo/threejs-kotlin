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
    var visible: Boolean
    var children: Array<Object3D>

    fun lookAt(vector: Vector3)
    fun add(pointLight: Object3D)
    fun rotateOnAxis(axis: Vector3, angle: Float)
    fun remove(obj3ct: Object3D)

    fun translateX(distance: Float)
    fun translateY(distance: Float)
    fun translateZ(distance: Float)
}