@file:JsQualifier("THREE")

package three.objects

import three.core.Geometry
import three.core.Object3D
import three.materials.Material


@JsName("Mesh")
external class Mesh(geometry: dynamic, material: dynamic) : Object3D {
    var material: Material
    var geometry: Geometry
}