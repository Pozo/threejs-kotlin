@file:JsQualifier("THREE")

package three.core

import js.JsArray
import three.math.Vector3

@JsName("Geometry")
external class Geometry {
    var vertices: JsArray<Vector3>
}

