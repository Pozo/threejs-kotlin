@file:JsQualifier("THREE")

package three.math

@JsName("Color")
external class Color {

    constructor()
    constructor(hex: String)
    constructor(hex: Int)
    constructor(color: Color)
    constructor(r: Number, g: Number, b: Number)

    var r: Double
    var g: Double
    var b: Double

    fun set(value: Color): Color
    fun set(value: Int): Color
    fun set(value: String): Color

    fun setScalar(scalar: Double): Color
    fun setRGB(r: Number, g: Number, b: Number): Color
    fun setHSL(hue: Float, saturation: Float, lightness: Float)
    fun setHex(hex: Int): Color
    fun getHex(): Int
    fun getHexString(): String

    fun clone(): Color
    fun copy(color: Color): Color
}

