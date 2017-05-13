@file:JsQualifier("THREE")

package three.geometries

import three.core.BufferGeometry

@JsName("SphereBufferGeometry")
external class SphereBufferGeometry : BufferGeometry {
    constructor(radius: Int, widthSegments: Int, heightSegments: Int, phiStart: Int, phiLength: Int, thetaStart: Int, thetaLength: Int)
    constructor(radius: Int, widthSegments: Int, heightSegments: Int, phiStart: Int, phiLength: Int, thetaStart: Int)
    constructor(radius: Int, widthSegments: Int, heightSegments: Int, phiStart: Int, phiLength: Int)
    constructor(radius: Int, widthSegments: Int, heightSegments: Int, phiStart: Int)
    constructor(radius: Int, widthSegments: Int, heightSegments: Int)
    constructor(radius: Int, widthSegments: Int)
    constructor(radius: Int)
}