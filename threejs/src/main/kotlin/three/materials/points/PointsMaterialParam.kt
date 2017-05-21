package three.materials.points

class PointsMaterialParam {
    var asDynamic: dynamic = {}

    var color: Int = 0
        set(value) {
            asDynamic.color = value
        }
}

