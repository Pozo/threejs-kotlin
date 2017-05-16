package examples.camera

import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import three.cameras.Camera
import three.cameras.OrthographicCamera
import three.cameras.PerspectiveCamera
import three.core.Geometry
import three.geometries.SphereBufferGeometry
import three.helpers.CameraHelper
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.materials.points.PointsMaterial
import three.materials.points.PointsMaterialParam
import three.math.Math
import three.math.Vector3
import three.objects.Group
import three.objects.Mesh
import three.objects.Points
import three.renderers.webglrenderer.WebGLRenderer
import three.renderers.webglrenderer.WebGLRendererParams
import three.scenes.Scene
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.Date

class WebglCamera {
    var SCREEN_WIDTH = window.innerWidth
    var SCREEN_HEIGHT = window.innerHeight
    var aspect = SCREEN_WIDTH / SCREEN_HEIGHT
    var frustumSize = 600

    private var activeCamera: Camera
    private var activeHelper: CameraHelper
    private var cameraOrtho: OrthographicCamera

    private var mesh: Mesh
    private var camera: PerspectiveCamera
    private var cameraPerspective: PerspectiveCamera
    private var cameraPerspectiveHelper: CameraHelper
    private var cameraOrthoHelper: CameraHelper

    private var renderer: WebGLRenderer
    private var scene: Scene
    private var cameraRig: Group

    init {
        val container = document.createElement("DIV")
        document.body?.appendChild(container)
        scene = Scene()
        //
        camera = PerspectiveCamera(50, 0.5 * aspect, 1.0, 10000)
        camera.position.z = 2500.0
        cameraPerspective = PerspectiveCamera(50, 0.5 * aspect, 150.0, 1000)
        cameraPerspectiveHelper = CameraHelper(cameraPerspective)
        scene.add(cameraPerspectiveHelper)
        //
        val left = 0.5 * frustumSize * aspect / -2
        val right = 0.5 * frustumSize * aspect / 2
        val top = frustumSize / 2
        val bottom = frustumSize / -2
        cameraOrtho = OrthographicCamera(left, right, top.toDouble(), bottom.toDouble(), 150.0, 1000.0)
        cameraOrthoHelper = CameraHelper(cameraOrtho)
        scene.add(cameraOrthoHelper)
        //
        activeCamera = cameraPerspective
        activeHelper = cameraPerspectiveHelper
        // counteract different front orientation of cameras vs rig

        cameraOrtho.rotation.y = kotlin.js.Math.PI // TODO
        cameraPerspective.rotation.y = kotlin.js.Math.PI // TODO
        cameraRig = Group()
        cameraRig.add(cameraPerspective)
        cameraRig.add(cameraOrtho)
        scene.add(cameraRig)
        //
        val params = MeshBasicMaterialParam()
        params.color = 0xffffff
        params.wireframe = true

        mesh = Mesh(
                SphereBufferGeometry(100, 16, 8),
                MeshBasicMaterial(params)
        )
        scene.add(mesh)

        val params2 = MeshBasicMaterialParam()
        params2.color = 0x00ff00
        params2.wireframe = true
        val mesh2 = Mesh(
                SphereBufferGeometry(50, 16, 8),
                MeshBasicMaterial(params2)
        )
        mesh2.position.y = 150.0
        mesh.add(mesh2)

        val params3 = MeshBasicMaterialParam()
        params3.color = 0x0000ff
        params3.wireframe = true
        val mesh3 = Mesh(
                SphereBufferGeometry(5, 16, 8),
                MeshBasicMaterial(params3)
        )
        mesh3.position.z = 150.0
        cameraRig.add(mesh3)
        //
        val geometry = Geometry()
        for (i in 0..top) {
            val x = Math.randFloatSpread(2000f)
            val y = Math.randFloatSpread(2000f)
            val z = Math.randFloatSpread(2000f)
            val vertex = Vector3(x, y, z)
            geometry.vertices.push(vertex)
        }

        val pointsMaterial = PointsMaterialParam()
        pointsMaterial.color = 0x888888
        val particles = Points(geometry, PointsMaterial(pointsMaterial))
        scene.add(particles)
        //
        val webGLRendererParams = WebGLRendererParams()
        webGLRendererParams.antialias = true
        renderer = WebGLRenderer(webGLRendererParams)
        renderer.setPixelRatio(window.devicePixelRatio)
        renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT)
        // TODO renderer.domElement.style.position = "relative"
        container.appendChild(renderer.domElement)
        renderer.autoClear = false

        //TODO val stats = Stats()
        //TODO container.appendChild(stats.dom)

        window.addEventListener("resize", { e: Event ->
            SCREEN_WIDTH = window.innerWidth
            SCREEN_HEIGHT = window.innerHeight
            aspect = SCREEN_WIDTH / SCREEN_HEIGHT
            renderer.setSize(SCREEN_WIDTH, SCREEN_HEIGHT)
            camera.aspect = 0.5 * aspect
            camera.updateProjectionMatrix()
            cameraPerspective.aspect = 0.5 * aspect
            cameraPerspective.updateProjectionMatrix()
            cameraOrtho.left = -0.5 * frustumSize * aspect / 2
            cameraOrtho.right = 0.5 * frustumSize * aspect / 2
            cameraOrtho.top = (frustumSize / 2).toDouble()
            cameraOrtho.bottom = -(frustumSize / 2).toDouble()
            cameraOrtho.updateProjectionMatrix()
        }, false)
        document.addEventListener("keydown", { event: Event ->
            if (event is KeyboardEvent) {

                when (event.keyCode) {
                    79 -> /*O*/ {
                        activeCamera = cameraOrtho
                        activeHelper = cameraOrthoHelper
                    }
                    80 -> /*P*/ {
                        activeCamera = cameraPerspective
                        activeHelper = cameraPerspectiveHelper
                    }
                }
            }

        }, false)
    }

    fun animate() {
        window.requestAnimationFrame({
            animate()
        })
        render()
        // TODO stats.update()
    }

    fun render() {
        val r = Date().getTime() * 0.0005
        mesh.position.x = 700 * kotlin.js.Math.cos(r)
        mesh.position.z = 700 * kotlin.js.Math.sin(r)
        mesh.position.y = 700 * kotlin.js.Math.sin(r)
        mesh.children.get(0).position.x = 70 * kotlin.js.Math.cos(2 * r)
        mesh.children.get(0).position.z = 70 * kotlin.js.Math.sin(r)
        if (activeCamera === cameraPerspective) {
            cameraPerspective.fov = 35 + 30 * kotlin.js.Math.sin(0.5 * r)
            cameraPerspective.far = mesh.position.length()
            cameraPerspective.updateProjectionMatrix()
            cameraPerspectiveHelper.update()
            cameraPerspectiveHelper.visible = true
            cameraOrthoHelper.visible = false
        } else {
            cameraOrtho.far = mesh.position.length()
            cameraOrtho.updateProjectionMatrix()
            cameraOrthoHelper.update()
            cameraOrthoHelper.visible = true
            cameraPerspectiveHelper.visible = false
        }
        cameraRig.lookAt(mesh.position)
        renderer.clear()
        activeHelper.visible = false
        renderer.setViewport(0, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT)
        renderer.render(scene, activeCamera)
        activeHelper.visible = true
        renderer.setViewport(SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT)
        renderer.render(scene, camera)
    }

}