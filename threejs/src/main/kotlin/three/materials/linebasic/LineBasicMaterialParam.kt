package three.materials.linebasic

class LineBasicMaterialParam {
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
    var transparent: Boolean = false
        set(value) {
            asDynamic.transparent = value
        }

}

