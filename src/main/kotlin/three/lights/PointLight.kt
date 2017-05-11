@file:JsQualifier("THREE")

package three.lights

@JsName("PointLight")
external class PointLight(color: Int, intensity: Int, distance: Int) : Light {
    val shadow: LightShadow
}