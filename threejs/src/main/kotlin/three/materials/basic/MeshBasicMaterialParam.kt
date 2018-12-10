package three.materials.basic

import three.textures.Texture

open class MeshBasicMaterialParam {
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
    var wireframe: Boolean = false
        set(value) {
            asDynamic.wireframe = value
        }
    var opacity: Double = 0.0
        set(value) {
            asDynamic.opacity = value
        }
    var transparent: Boolean = false
        set(value) {
            asDynamic.transparent = value
        }
    var visible: Boolean = true
        set(value) {
            asDynamic.visible = value
        }
    var map: Texture? = null
        set(value) {
            asDynamic.map = value
        }
    var flatShading: Boolean = false
        set(value) {
            asDynamic.flatShading = value
        }
}

