package three.materials.phong

import three.materials.MaterialColors
import three.materials.basic.MeshBasicMaterialParam

class MeshPhongMaterialParam : MeshBasicMaterialParam {

    // XXX here we have an issue with dynamic initialisation
    constructor() : this(js("new Object()"))

    private constructor(asDynamic: dynamic) {
        this.asDynamic = asDynamic
    }

    var specular: Int = 0
        set(value) {
            asDynamic.specular = value
        }
    var shininess: Int = 0
        set(value) {
            asDynamic.shininess = value
        }
    var shading: Int = 0
        set(value) {
            asDynamic.shading = value
        }
    var vertexColors: MaterialColors = MaterialColors.NoColors
        set(value) {
            asDynamic.vertexColors = value.value
        }
}

