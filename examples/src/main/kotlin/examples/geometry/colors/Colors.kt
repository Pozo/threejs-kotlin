package examples.geometry.colors

import org.khronos.webgl.Float32Array
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Element
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import three.Shading
import three.cameras.Camera
import three.cameras.PerspectiveCamera
import three.core.BufferAttribute
import three.geometries.IcosahedronBufferGeometry
import three.geometries.PlaneBufferGeometry
import three.lights.DirectionalLight
import three.materials.MaterialColors
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.materials.phong.MeshPhongMaterial
import three.materials.phong.MeshPhongMaterialParam
import three.math.Color
import three.objects.Mesh
import three.renderers.webglrenderer.WebGLRenderer
import three.renderers.webglrenderer.WebGLRendererParams
import three.scenes.Scene
import three.textures.CanvasTexture
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI

class Colors {
    private val container: Element
    //    var stats
    private val camera: Camera
    private val scene: Scene
    private val renderer: WebGLRenderer

    private var mouseX = 0
    private var mouseY = 0
    private var windowHalfX = window.innerWidth / 2
    private var windowHalfY = window.innerHeight / 2

    init {
        container = document.getElementById("container")!!
        camera = PerspectiveCamera(20, window.innerWidth / window.innerHeight, 1, 10000)
        camera.position.z = 1800.0
        scene = Scene()
        scene.background = Color(0xffffff)
        val light = DirectionalLight(0xffffff)
        light.position.set(0f, 0f, 1f)
        scene.add(light)
        // shadow
        val canvas = document.createElement("canvas") as HTMLCanvasElement
        canvas.width = 128
        canvas.height = 128
        val context = canvas.getContext("2d") as CanvasRenderingContext2D
        val gradient = context.createRadialGradient(canvas.width / 2.0, canvas.height / 2.0, 0.0, canvas.width / 2.0, canvas.height / 2.0, canvas.width / 2.0)
        gradient.addColorStop(0.1, "rgba(210,210,210,1)")
        gradient.addColorStop(1.0, "rgba(255,255,255,1)")
        context.fillStyle = gradient
        context.fillRect(0.0, 0.0, canvas.width * 1.0, canvas.height * 1.0)
        val shadowTexture = CanvasTexture(canvas)
        val param = MeshBasicMaterialParam()
        param.map = shadowTexture

        val shadowMaterial = MeshBasicMaterial(param)
        val shadowGeo = PlaneBufferGeometry(300f, 300f, 1, 1)
        var shadowMesh = Mesh(shadowGeo, shadowMaterial)
        shadowMesh.position.y = -250.0
        shadowMesh.rotation.x = -PI / 2
        scene.add(shadowMesh)
        shadowMesh = Mesh(shadowGeo, shadowMaterial)
        shadowMesh.position.y = -250.0
        shadowMesh.position.x = -400.0
        shadowMesh.rotation.x = -PI / 2
        scene.add(shadowMesh)
        shadowMesh = Mesh(shadowGeo, shadowMaterial)
        shadowMesh.position.y = -250.0
        shadowMesh.position.x = 400.0
        shadowMesh.rotation.x = -PI / 2
        scene.add(shadowMesh)
        val radius = 200f
        val geometry1 = IcosahedronBufferGeometry(radius, 1)
        val count = geometry1.attributes.position.count as Int
        geometry1.addAttribute("color", BufferAttribute(Float32Array((count * 3)), 3))
        val geometry2 = geometry1.clone()
        val geometry3 = geometry1.clone()
        val color = Color()
        val positions1 = geometry1.attributes.position
        val positions2 = geometry2.attributes.position
        val positions3 = geometry3.attributes.position
        val colors1 = geometry1.attributes.color
        val colors2 = geometry2.attributes.color
        val colors3 = geometry3.attributes.color

        for (i in 0..count) {
            color.setHSL((positions1.getY(i) / radius + 1) / 2, 1f, 0.5f)
            colors1.setXYZ(i, color.r, color.g, color.b)
            color.setHSL(0f, (positions2.getY(i) / radius + 1) / 2, 0.5f)
            colors2.setXYZ(i, color.r, color.g, color.b)
            val b = ((positions3.getY(i) / radius + 1) / 2.0) as Float
            color.setRGB(1, 0.8 - b, 0)
            colors3.setXYZ(i, color.r, color.g, color.b)
        }

        val phongParam = MeshPhongMaterialParam()

        phongParam.color = 0xffffff
        phongParam.shininess = 0
        phongParam.flatShading = true
        phongParam.vertexColors = MaterialColors.VertexColors

        val material = MeshPhongMaterial(phongParam)

        val meshParam = MeshBasicMaterialParam()
        meshParam.color = 0x000000
        meshParam.wireframe = true
        meshParam.transparent = true

        val wireframeMaterial = MeshBasicMaterial(meshParam)
        val mesh1 = Mesh(geometry1, material)
        val wireframe1 = Mesh(geometry1, wireframeMaterial)
        mesh1.add(wireframe1)
        mesh1.position.x = -400.0
        mesh1.rotation.x = -1.87
        scene.add(mesh1)
        val mesh2 = Mesh(geometry2, material)
        val wireframe2 = Mesh(geometry2, wireframeMaterial)
        mesh2.add(wireframe2)
        mesh2.position.x = 400.0
        scene.add(mesh2)
        val mesh3 = Mesh(geometry3, material)
        val wireframe3 = Mesh(geometry3, wireframeMaterial)
        mesh3.add(wireframe3)
        scene.add(mesh3)

        val webGLRendererParams = WebGLRendererParams()
        webGLRendererParams.antialias = true

        renderer = WebGLRenderer(webGLRendererParams)
        renderer.setPixelRatio(window.devicePixelRatio)
        renderer.setSize(window.innerWidth, window.innerHeight)
        container.appendChild(renderer.domElement)
//        stats = new Stats ()
//        container.appendChild(stats.dom)
        document.addEventListener("mousemove", { event: Event ->
            if (event is MouseEvent) {
                mouseX = (event.clientX - windowHalfX)
                mouseY = (event.clientY - windowHalfY)
            }

        }, false)
        //
        window.addEventListener("resize", {
            windowHalfX = window.innerWidth / 2
            windowHalfY = window.innerHeight / 2
            camera.aspect = window.innerWidth / window.innerHeight
            camera.updateProjectionMatrix()
            renderer.setSize(window.innerWidth, window.innerHeight)
        }, false)

        animate()
    }

    //
    private fun animate() {
        window.requestAnimationFrame { animate() }
        render()
        // TODO stats.update()
    }

    fun render() {
        camera.position.x += (mouseX - camera.position.x) * 0.05
        camera.position.y += (-mouseY - camera.position.y) * 0.05
        camera.lookAt(scene.position)
        renderer.render(scene, camera)
    }

}
