@file:JsQualifier("THREE")

package three.lights

@JsName("AmbientLight")
external class AmbientLight : Light {
    constructor(color: Int, intensity: Int)
    constructor(color: Int)
}
