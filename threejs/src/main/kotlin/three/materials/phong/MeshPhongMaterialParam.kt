package three.materials.phong

import three.textures.Texture

class MeshPhongMaterialParam {
    var asDynamic: dynamic

    // XXX here we have an issue with dynamic initialisation
    constructor() : this(js("new Object()"))

    private constructor(asDynamic: dynamic) {
        this.asDynamic = asDynamic
    }

    var color: Int = 0
        set(value) {
            asDynamic.color = value
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
    var map: Texture = Texture()
        set(value) {
            asDynamic.map = value
        }
}

