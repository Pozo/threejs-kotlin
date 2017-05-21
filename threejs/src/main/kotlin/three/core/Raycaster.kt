@file:JsQualifier("THREE")

package three.core

import three.cameras.Camera
import three.math.Vector2
import three.math.Vector3
import three.objects.Mesh

@JsName("Raycaster")
external open class Raycaster {
    constructor(origin: Vector3, direction: Vector3, near: Float, far: Float)
    constructor(origin: Vector3, direction: Vector3, near: Float)
    constructor(origin: Vector3, direction: Vector3)
    constructor(origin: Vector3)
    constructor()

    fun intersectObjects(objects: Array<Mesh>): Array<dynamic> // TODO JsArray<Intersection>
    fun intersectObjects(objects: Array<Mesh>, recursive: Boolean): Array<dynamic> // TODO JsArray<Intersection>
    fun setFromCamera(mouse: Vector2, camera: Camera)
}