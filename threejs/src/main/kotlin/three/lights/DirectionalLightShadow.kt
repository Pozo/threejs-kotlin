@file:JsQualifier("THREE")

package three.lights

import three.cameras.Camera
import three.cameras.OrthographicCamera

@JsName("LightShadow")
external class DirectionalLightShadow(camera: Camera) : LightShadow {
    override val camera: OrthographicCamera
}

