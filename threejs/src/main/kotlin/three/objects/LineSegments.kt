@file:JsQualifier("THREE")

package three.objects

import three.core.Geometry
import three.materials.Material

@JsName("LineSegments")
open external class LineSegments(geometry: Geometry, material: Material) : Line
