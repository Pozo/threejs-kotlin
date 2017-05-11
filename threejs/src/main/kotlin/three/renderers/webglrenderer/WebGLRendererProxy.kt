@file:JsQualifier("THREE")

package three.renderers.webglrenderer

import org.w3c.dom.Node
import three.cameras.Camera
import three.math.Color
import three.renderers.webgl.WebGLShadowMap
import three.scenes.Scene

@JsName("WebGLRenderer")
external open class WebGLRendererProxy {
    protected constructor()
    protected constructor(params: dynamic)

    val domElement: Node
    var shadowMap: WebGLShadowMap

    fun setSize(innerWidth: Int, innerHeight: Int)
    fun render(scene: Scene, camera: Camera)
    fun setClearColor(color: Color)
    fun setPixelRatio(devicePixelRatio: Double)
}