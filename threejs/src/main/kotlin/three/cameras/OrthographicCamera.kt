@file:JsQualifier("THREE")

package three.cameras

@JsName("OrthographicCamera")
external open class OrthographicCamera(left: Double, right: Double, top: Double, bottom: Double, near: Double, far: Double) : Camera {
    var left: Double
    var right: Double
    var top: Double
    var bottom: Double
    var near: Double
    var far: Double

    fun updateProjectionMatrix()
}