@file:JsQualifier("THREE")

package three.lights

import three.core.Object3D
import three.math.Color

@JsName("Light")
external open class Light(color: Int, intensity: Float) : Object3D {
    var color: Color
}
