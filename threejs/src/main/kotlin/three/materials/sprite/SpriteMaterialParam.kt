package three.materials.sprite

import three.textures.Texture

class SpriteMaterialParam {
    var asDynamic: dynamic

    constructor() {
        asDynamic = {}
    }

    var color: Int = 0
        set(value) {
            asDynamic.color = value
        }
    var opacity: Float = 0f
        set(value) {
            asDynamic.opacity = value
        }
    var map: Texture = Texture()
        set(value) {
            asDynamic.map = value
        }
}

