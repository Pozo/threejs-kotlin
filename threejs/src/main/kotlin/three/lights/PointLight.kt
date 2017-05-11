@file:JsQualifier("THREE")

package three.lights

@JsName("PointLight")
external class PointLight : Light {
    constructor(color: Int, intensity: Double, distance: Int)
    constructor(color: Int, intensity: Double)

    val shadow: LightShadow
}