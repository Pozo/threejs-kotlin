package examples.interactive.voxelpainter

import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import three.cameras.Camera
import three.cameras.PerspectiveCamera
import three.core.Geometry
import three.core.Raycaster
import three.geometries.BoxGeometry
import three.geometries.PlaneBufferGeometry
import three.lights.AmbientLight
import three.lights.DirectionalLight
import three.loaders.TextureLoader
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.materials.linebasic.LineBasicMaterial
import three.materials.linebasic.LineBasicMaterialParam
import three.materials.meshlambert.MeshLambertMaterial
import three.materials.meshlambert.MeshLambertMaterialParam
import three.math.Color
import three.math.Vector2
import three.math.Vector3
import three.objects.LineSegments
import three.objects.Mesh
import three.renderers.webglrenderer.WebGLRenderer
import three.renderers.webglrenderer.WebGLRendererParams
import three.scenes.Scene
import kotlin.browser.document
import kotlin.browser.window

class VoxelPainter {
    var rollOverGeo: BoxGeometry
    var container: Element
    val camera: Camera
    var scene: Scene
    var renderer: WebGLRenderer
    var plane: Mesh
    var mouse: Vector2
    var raycaster: Raycaster
    var isShiftDown = false
    var rollOverMesh: Mesh
    var rollOverMaterial: MeshBasicMaterial
    var cubeGeo: BoxGeometry
    var cubeMaterial: MeshLambertMaterial
    var objects: ArrayList<Mesh> = ArrayList()

    init {
        container = document.createElement("div")
        document.body?.appendChild(container)
        val info = document.createElement("div")
// TODO       info.style.position = "absolute"
// TODO        info.style.top = "10px"
// TODO        info.style.width = "100%"
// TODO        info.style.textAlign = "center"
//        info.innerHTML = "<a href="http://threejs.org" target="_blank">three.js</a> - voxel painter - webgl<br><strong>click</strong>: add voxel, <strong>shift + click</strong>: remove voxel"
        container.appendChild(info)
        camera = PerspectiveCamera(45, window.innerWidth / window.innerHeight, 1, 10000)
        camera.position.set(500f, 800f, 1300f)
        camera.lookAt(Vector3(0f, 0f, 0f))
        scene = Scene()
        // roll-over helpers
        rollOverGeo = BoxGeometry(50, 50, 50)
        val meshParam = MeshBasicMaterialParam()
        meshParam.color = 0xff0000
        meshParam.opacity = 0.5
        meshParam.transparent = true
        rollOverMaterial = MeshBasicMaterial(meshParam)
        rollOverMesh = Mesh(rollOverGeo, rollOverMaterial)
        scene.add(rollOverMesh)
        // cubes
        cubeGeo = BoxGeometry(50, 50, 50)
        val textureLoader = TextureLoader()
        val lambertMaterialParams = MeshLambertMaterialParam()
        lambertMaterialParams.color = 0xfeb74c
        lambertMaterialParams.map = textureLoader.load("square-outline-textured.png")

        cubeMaterial = MeshLambertMaterial(lambertMaterialParams)
        // grid
        val size = 500
        val step = 50
        val geometry = Geometry()

        for (i in -size..size step step) {
            geometry.vertices.push(Vector3(-size.toFloat(), 0f, i.toFloat()))
            geometry.vertices.push(Vector3(size.toFloat(), 0f, i.toFloat()))
            geometry.vertices.push(Vector3(i.toFloat(), 0f, -size.toFloat()))
            geometry.vertices.push(Vector3(i.toFloat(), 0f, size.toFloat()))
        }
        val basicMaterialParams = LineBasicMaterialParam()
        basicMaterialParams.color = 0x000000
        basicMaterialParams.opacity = 0.2
        basicMaterialParams.transparent = true
        val material = LineBasicMaterial(basicMaterialParams)
        val line = LineSegments(geometry, material)
        scene.add(line)
        //
        raycaster = Raycaster()
        mouse = Vector2()
        val geometry2 = PlaneBufferGeometry(1000f, 1000f)
        geometry2.rotateX((-kotlin.js.Math.PI / 2).toFloat())
        val planeParams = MeshBasicMaterialParam()
        planeParams.visible = false
        plane = Mesh(geometry2, MeshBasicMaterial(planeParams))
        scene.add(plane)
        objects.add(plane)
        // Lights
        val ambientLight = AmbientLight(0x606060)
        scene.add(ambientLight)
        val directionalLight = DirectionalLight(0xffffff, 0.5f)
        directionalLight.position.set(1f, 0.75f, 0.5f).normalize()
        scene.add(directionalLight)
        val params = WebGLRendererParams()
        params.antialias = true
        renderer = WebGLRenderer(params)
        renderer.setClearColor(Color(0xf0f0f0))
        renderer.setPixelRatio(window.devicePixelRatio)
        renderer.setSize(window.innerWidth, window.innerHeight)
        container.appendChild(renderer.domElement)

        document.addEventListener("mousemove", { event: Event ->
            if (event is MouseEvent) {
                println("mousemove")
                event.preventDefault()
                mouse.set(((event.clientX / window.innerWidth) * 2 - 1).toFloat(), (-(event.clientY / window.innerHeight) * 2 + 1).toFloat())
                raycaster.setFromCamera(mouse, camera)
                val intersects = raycaster.intersectObjects(objects.toTypedArray())
                if (intersects.size > 0) {
                    val intersect = intersects.get(0)
                    rollOverMesh.position.copy(intersect.point).add(intersect.face.normal)
                    rollOverMesh.position.divideScalar(50f).floor().multiplyScalar(50f).addScalar(25f)
                }
                render()
            }

        }, false)
        document.addEventListener("mousedown", { event: Event ->
            if (event is MouseEvent) {
                event.preventDefault()
                mouse.set(((event.clientX / window.innerWidth) * 2 - 1).toFloat(), (-(event.clientY / window.innerHeight) * 2 + 1).toFloat())
                raycaster.setFromCamera(mouse, camera)
                val intersects = raycaster.intersectObjects(objects.toTypedArray())
                if (intersects.size > 0) {
                    val intersect = intersects.get(0)
                    // delete cube
                    if (isShiftDown) {
                        if (js("intersect.object") != plane) {
                            scene.remove(js("intersect.object"))
                            val indexOf = objects.indexOf(js("intersect.object"))
                            objects.removeAt(indexOf)
                        }
                        // create cube
                    } else {
                        var voxel = Mesh(cubeGeo, cubeMaterial)
                        voxel.position.copy(intersect.point).add(intersect.face.normal)
                        voxel.position.divideScalar(50f).floor().multiplyScalar(50f).addScalar(25f)
                        scene.add(voxel)
                        objects.add(voxel)
                    }
                    render()
                }
            }
        }, false)
        document.addEventListener("keydown", { event: Event ->
            if (event is KeyboardEvent) {
                when (event.keyCode) {
                    16 -> isShiftDown = true
                }
            }

        }, false)
        document.addEventListener("keyup", { event: Event ->
            if (event is KeyboardEvent) {
                when (event.keyCode) {
                    16 -> isShiftDown = false
                }
            }

        }, false)
        //
        window.addEventListener("resize", { event: Event ->
            camera.aspect = window.innerWidth / window.innerHeight
            camera.updateProjectionMatrix()
            renderer.setSize(window.innerWidth, window.innerHeight)
        }, false)
        render()
    }

    fun render() {
        renderer.render(scene, camera)
    }
}

