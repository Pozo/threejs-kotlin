@file:JsQualifier("THREE")

package three.cameras

@JsName("PerspectiveCamera")
external class PerspectiveCamera(fov: Number, aspect: Number, near: Number, far: Number) : Camera {
    var fov: Number
    var aspect: Number
    var near: Number
    var far: Number

    fun updateProjectionMatrix()
}
