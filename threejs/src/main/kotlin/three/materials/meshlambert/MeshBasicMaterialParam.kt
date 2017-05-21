package three.materials.meshlambert

import three.textures.Texture

class MeshLambertMaterialParam {
    var asDynamic: dynamic = {}

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

}

