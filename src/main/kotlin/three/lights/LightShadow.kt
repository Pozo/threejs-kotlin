@file:JsQualifier("THREE")

package three.lights

import three.cameras.Camera
import three.math.Vector2

@JsName("LightShadow")
external class LightShadow(camera: Camera) {
    var mapSize: Vector2
    val camera: Camera
}

