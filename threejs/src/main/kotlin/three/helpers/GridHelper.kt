@file:JsQualifier("THREE")

package three.helpers

import three.objects.Line


@JsName("GridHelper")
external class GridHelper : Line {
    constructor(size: Int)
    constructor(size: Int, divisions: Int)
    constructor(size: Int, divisions: Int, colorCenterLine: Int)
    constructor(size: Int, divisions: Int, colorCenterLine: Int, colorGrid: Int)
}

