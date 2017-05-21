@file:JsQualifier("THREE")

package three.lights

@JsName("HemisphereLight")
external class HemisphereLight : Light {
    constructor(skyColor: Int, groundColor: Int, intensity: Float)
    constructor(skyColor: Int, groundColor: Int)
    constructor(skyColor: Int)
    constructor()
}
