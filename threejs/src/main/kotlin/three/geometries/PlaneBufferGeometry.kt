@file:JsQualifier("THREE")

package three.geometries

import three.core.BufferGeometry

@JsName("PlaneBufferGeometry")
external class PlaneBufferGeometry(width: Float, height: Float) : BufferGeometry {
    constructor(width: Float, height: Float, widthSegments: Int)
    constructor(width: Float, height: Float, widthSegments: Int, heightSegments: Int)
}