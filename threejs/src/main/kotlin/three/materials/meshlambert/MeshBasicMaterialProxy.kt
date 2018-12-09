@file:JsQualifier("THREE")

package three.materials.meshlambert

import three.materials.Material
import three.math.Color

@JsName("MeshLambertMaterial")
open external class MeshLambertMaterialProxy protected constructor(params: dynamic) : Material {
    var color: Color
}
