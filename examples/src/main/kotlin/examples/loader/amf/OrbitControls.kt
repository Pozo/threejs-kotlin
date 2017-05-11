@file:JsQualifier("THREE")

package examples.loader.amf

import org.w3c.dom.Node
import three.core.Object3D
import three.math.Vector3

//examples/js/controls/OrbitControls.js
@JsName("OrbitControls")
external class OrbitControls(obj: Object3D, domElement: Node) : Node {
    val target: Vector3

    fun update()
}

