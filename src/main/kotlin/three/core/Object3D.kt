@file:JsQualifier("THREE")

package three.core

import three.math.Euler

@JsName("Object3D")
external open class Object3D {
    var rotation: Euler
    var castShadow: Boolean
}