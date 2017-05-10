@file:JsQualifier("THREE")

package three.renderers

import org.w3c.dom.Node
import three.cameras.Camera
import three.renderers.webgl.WebGLShadowMap
import three.scenes.Scene

@JsName("WebGLRenderer")
external class WebGLRenderer {
    val domElement: Node
    fun setSize(innerWidth: Int, innerHeight: Int)
    fun render(scene: Scene, camera: Camera)
    var shadowMap: WebGLShadowMap
}