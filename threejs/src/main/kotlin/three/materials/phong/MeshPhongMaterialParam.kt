package three.materials.phong

class MeshPhongMaterialParam {
    var asDynamic: dynamic = {}

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
}

