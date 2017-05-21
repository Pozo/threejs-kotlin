package three.materials.points

class PointsMaterialParam {
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
}

