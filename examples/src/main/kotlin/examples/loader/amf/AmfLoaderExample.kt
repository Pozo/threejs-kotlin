package examples.loader.amf

import org.w3c.dom.events.Event
import three.cameras.PerspectiveCamera
import three.helpers.GridHelper
import three.lights.AmbientLight
import three.lights.PointLight
import three.math.Color
import three.math.Vector3
import three.renderers.webglrenderer.WebGLRenderer
import three.renderers.webglrenderer.WebGLRendererParams
import three.scenes.Scene
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Math

class AmfLoaderExample {
    val renderer: WebGLRenderer
    val scene: Scene
    val camera: PerspectiveCamera

    init {
        scene = Scene()
        scene.add(AmbientLight(0x999999))
        camera = PerspectiveCamera(35, (window.innerWidth / window.innerHeight).toDouble(), 1.0, 500)
        // Z is up for objects intended to be 3D printed.
        camera.up.set(0f, 0f, 1f)
        camera.position.set(0f, -9f, 6f)
        camera.add(PointLight(0xffffff, 0.8))
        scene.add(camera)
        val grid = GridHelper(50, 50, 0xffffff, 0x555555)
        val vector3 = Vector3(1f, 0f, 0f)
        grid.rotateOnAxis(vector3, 90 * (Math.PI / 180))
        scene.add(grid)
        val options = WebGLRendererParams()
        options.antialias = true
        renderer = WebGLRenderer(options)
        renderer.setClearColor(Color(0x999999))
        renderer.setPixelRatio(window.devicePixelRatio)
        renderer.setSize(window.innerWidth, window.innerHeight)
        document.body?.appendChild(renderer.domElement)
        val loader = AMFLoader()

        loader.load("https://rawgit.com/mrdoob/three.js/master/examples/models/amf/rook.amf", { object3D ->
            println(object3D)
            scene.add(object3D)
            render()
        })
//
        val controls = OrbitControls(camera, renderer.domElement)
        controls.addEventListener("change", {
            render()
        })
        controls.target.set(0f, 1f, 2f)
        controls.update()

        window.addEventListener("resize", { e: Event ->
            camera.aspect = window.innerWidth / window.innerHeight
            camera.updateProjectionMatrix()
            renderer.setSize(window.innerWidth, window.innerHeight)
            render()
        }, false)
    }

    fun render() {
        renderer.render(scene, camera)
    }
}

