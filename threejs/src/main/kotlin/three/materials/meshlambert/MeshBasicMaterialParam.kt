package three.materials.meshlambert

import three.textures.Texture

class MeshLambertMaterialParam {
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
    var map: Texture = Texture()
        set(value) {
            asDynamic.map = value
        }
    var lightMap: Texture = Texture()
        set(value) {
            asDynamic.lightMap = value
        }
    var lightMapIntensity: Float = 0f
        set(value) {
            asDynamic.lightMapIntensity = value
        }
    var reflectivity: Float = 0f
        set(value) {
            asDynamic.reflectivity = value
        }
    var refractionRatio: Float = 0f
        set(value) {
            asDynamic.refractionRatio = value
        }
}

