@file:JsQualifier("THREE")

package three.cameras

import three.math.Vector3

@JsName("PerspectiveCamera")
external class PerspectiveCamera(fov: Int, aspect: Int, near: Double, far: Int) : Camera {
    val position: Vector3

}

