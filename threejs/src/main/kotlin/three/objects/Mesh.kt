@file:JsQualifier("THREE")

package three.objects

import three.core.Geometry
import three.core.Object3D
import three.materials.Material


@JsName("Mesh")
external class Mesh : Object3D {

    constructor(
            geometry: dynamic = definedExternally,
            material: dynamic = definedExternally
    )

    var material: Material
    var geometry: Geometry
}