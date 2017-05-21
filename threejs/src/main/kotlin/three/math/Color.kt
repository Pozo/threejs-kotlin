@file:JsQualifier("THREE")

package three.math

@JsName("Color")
external class Color(hex: Int) {
    fun setHSL(hue: Float, saturation: Float, lightness: Float)
}

