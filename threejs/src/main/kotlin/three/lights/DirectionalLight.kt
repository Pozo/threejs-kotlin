@file:JsQualifier("THREE")

package three.lights

@JsName("DirectionalLight")
external class DirectionalLight : Light {
    constructor(color: Int, intensity: Float)
    constructor(color: Int)

    val shadow: DirectionalLightShadow
}
