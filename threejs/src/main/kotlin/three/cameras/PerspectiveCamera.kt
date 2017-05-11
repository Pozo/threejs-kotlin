@file:JsQualifier("THREE")

package three.cameras

@JsName("PerspectiveCamera")
external class PerspectiveCamera(fov: Int, aspect: Int, near: Double, far: Int) : Camera {
    var aspect: Int

    fun updateProjectionMatrix()
}
