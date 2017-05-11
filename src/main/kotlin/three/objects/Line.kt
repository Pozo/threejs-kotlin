@file:JsQualifier("THREE")

package three.objects

import three.core.Geometry
import three.core.Object3D
import three.materials.Material

@JsName("Line")
external open class Line(geometry: Geometry, material: Material) : Object3D

