@file:JsQualifier("THREE")

package three.lights

import three.cameras.Camera
import three.math.Vector2

@JsName("LightShadow")
open external class LightShadow(camera: Camera) {
    open val camera: Camera

    var mapSize: Vector2
    var bias: Float
    var radius: Float
}

