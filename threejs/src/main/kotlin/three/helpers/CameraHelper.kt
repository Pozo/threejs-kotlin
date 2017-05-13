@file:JsQualifier("THREE")

package three.helpers

import three.cameras.Camera
import three.objects.LineSegments

@JsName("CameraHelper")
external class CameraHelper(camera: Camera) : LineSegments {
    fun update()
}

