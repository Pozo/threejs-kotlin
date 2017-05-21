package examples.interactive.voxelpainter

import examples.board.RTSCameraControl
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent
import three.ShadowMap
import three.cameras.Camera
import three.cameras.PerspectiveCamera
import three.core.Clock
import three.core.Geometry
import three.core.Raycaster
import three.geometries.BoxGeometry
import three.geometries.PlaneBufferGeometry
import three.lights.AmbientLight
import three.lights.DirectionalLight
import three.lights.Light
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
import kotlin.js.Math

class Board {
    var container: Element
    val camera: Camera
    var scene: Scene
    var renderer: WebGLRenderer
    var plane: Mesh
    var mouse: Vector2
    var raycaster: Raycaster
    var rollOverMesh: Mesh
    var cubeGeo: BoxGeometry
    var objects: ArrayList<Mesh> = ArrayList()

    private var control: RTSCameraControl
    private val clock = Clock()
    private var textureLoader: TextureLoader

    var isShiftDown = false
    var isCtrlDown = false

    init {
        container = document.createElement("div")
        document.body?.appendChild(container)

        textureLoader = TextureLoader()

        camera = createCamera()
        scene = Scene()

        // roll-over helpers
        rollOverMesh = createRollOverMesh()
        scene.add(rollOverMesh)

        // cubes

        cubeGeo = BoxGeometry(50, 50, 50)

        // grid
        scene.add(createGrid())

        //

        raycaster = Raycaster()
        mouse = Vector2()

        plane = createPlane()
        scene.add(plane)
        objects.add(plane)

        // Lights

        scene.add(AmbientLight(0x666666))
        scene.add(createSun())

        renderer = createRenderer()

        control = RTSCameraControl(camera, document)
        container.appendChild(renderer.domElement)

        document.addEventListener("mousemove", { event: Event ->
            if (event is MouseEvent) {
                event.preventDefault()

                mouse.set(
                        ((event.clientX.toFloat() / window.innerWidth.toFloat()) * 2) - 1,
                        -((event.clientY.toFloat() / window.innerHeight.toFloat()) * 2) + 1
                )
                raycaster.setFromCamera(mouse, camera)
                val intersects = raycaster.intersectObjects(objects.toTypedArray())

                if (intersects.isNotEmpty()) {
                    val intersect = intersects.get(0)
                    rollOverMesh.position.copy(intersect.point).add(intersect.face.normal)
                    rollOverMesh.position.divideScalar(50f).floor().multiplyScalar(50f).addScalar(25f)
                }
            }

        }, false)
        document.addEventListener("mousedown", { event: Event ->
            if (event is MouseEvent) {
                event.preventDefault()
                mouse.set(
                        ((event.clientX.toFloat() / window.innerWidth.toFloat()) * 2) - 1,
                        -((event.clientY.toFloat() / window.innerHeight.toFloat()) * 2) + 1
                )

                raycaster.setFromCamera(mouse, camera)
                val intersects = raycaster.intersectObjects(objects.toTypedArray())
                if (intersects.isNotEmpty()) {
                    val intersect = intersects.get(0)
                    // delete cube
                    if (isShiftDown) {
                        if (js("intersect.object") != plane) {
                            scene.remove(js("intersect.object"))
                            val indexOf = objects.indexOf(js("intersect.object"))
                            objects.removeAt(indexOf)
                        }
                        // create cube
                    } else if (isCtrlDown) {
                        if (js("intersect.object") != plane) {
                            val indexOf = objects.indexOf(js("intersect.object"))
                            val mesh = objects.get(indexOf)

                            val material = mesh.material
                            if (material is MeshLambertMaterial) {
                                material.color = Color(0x00FF00)
                            }
                        }
                    } else {

                        val lambertMaterialParams = MeshLambertMaterialParam()
                        lambertMaterialParams.color = 0xdedede
                        lambertMaterialParams.map = textureLoader.load("square-outline-textured.png")

                        val cubeMaterial = MeshLambertMaterial(lambertMaterialParams)

                        val voxel = Mesh(cubeGeo, cubeMaterial)
                        voxel.castShadow = true
                        voxel.receiveShadow = true
                        voxel.position.copy(intersect.point).add(intersect.face.normal)
                        voxel.position.divideScalar(50f).floor().multiplyScalar(50f).addScalar(25f)
                        scene.add(voxel)
                        objects.add(voxel)
                    }
                }
            }
        }, false)
        document.addEventListener("keydown", { event: Event ->
            if (event is KeyboardEvent) {
                when (event.keyCode) {
                    16 -> isShiftDown = true
                    17 -> isCtrlDown = true
                }
            }

        }, false)
        document.addEventListener("keyup", { event: Event ->
            if (event is KeyboardEvent) {
                when (event.keyCode) {
                    16 -> isShiftDown = false
                    17 -> isCtrlDown = false
                }
            }

        }, false)
        //
        window.addEventListener("resize", { event: Event ->
            camera.aspect = window.innerWidth.toFloat() / window.innerHeight.toFloat()
            camera.updateProjectionMatrix()
            renderer.setSize(window.innerWidth, window.innerHeight)
        }, false)

        render()
    }

    private fun createRenderer(): WebGLRenderer {
        val params = WebGLRendererParams()
        params.antialias = true
        val renderer = WebGLRenderer(params)
        renderer.setSize(window.innerWidth, window.innerHeight)
        renderer.shadowMap.enabled = true
        renderer.shadowMap.type = ShadowMap.PCFSoftShadowMap.value

        renderer.setClearColor(Color(0xf0f0f0))
        renderer.setPixelRatio(window.devicePixelRatio)


        return renderer
    }

    private fun createGrid(): LineSegments {
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
        return LineSegments(geometry, material)
    }

    private fun createRollOverMesh(): Mesh {
        val rollOverGeo = BoxGeometry(50, 50, 50)

        val meshParam = MeshBasicMaterialParam()
        meshParam.color = 0xff0000
        meshParam.opacity = 0.5
        meshParam.transparent = true
        val rollOverMaterial = MeshBasicMaterial(meshParam)

        return Mesh(rollOverGeo, rollOverMaterial)
    }

    private fun createCamera(): PerspectiveCamera {
        val camera = PerspectiveCamera(45, window.innerWidth.toFloat() / window.innerHeight.toFloat(), 1, 5000)
        camera.position.set(0f, 500f, 500f)
        camera.lookAt(Vector3())

        return camera
    }

    private fun createPlane(): Mesh {
        val planeGeomerty = PlaneBufferGeometry(1000f, 1000f)
        planeGeomerty.rotateX(-(Math.PI / 2).toFloat())

        val planeParams = MeshLambertMaterialParam()
        planeParams.map = textureLoader.load("grass.png")
        val planeMaterial = MeshLambertMaterial(planeParams)

        val plane = Mesh(planeGeomerty, planeMaterial)
        plane.receiveShadow = true
        plane.castShadow = false

        return plane
    }
    // based on https://github.com/dirkk0/threejs_daynight/blob/master/index.html
    private fun createSun(): Light {
        val dirLight = DirectionalLight(0xdfebff, 1f)
        dirLight.position.set(300f, 500f, 50f)
        dirLight.position.multiplyScalar(1.3f)

        dirLight.castShadow = true

        dirLight.shadow.mapSize.height = (1024 * 2).toFloat()
        dirLight.shadow.mapSize.width = (1024 * 2).toFloat()
        val d = 2000.0
        dirLight.shadow.camera.left = -d
        dirLight.shadow.camera.right = d
        dirLight.shadow.camera.top = d
        dirLight.shadow.camera.bottom = -d
        dirLight.shadow.camera.visible = true
        // the magic is here - this needs to be tweaked if you change dimensions
        dirLight.shadow.camera.far = 10000.0
        dirLight.shadow.bias = -0.000001f

        return dirLight
    }

    fun render() {
        renderer.render(scene, camera)
        control.update(clock.getDelta())
        window.requestAnimationFrame({
            render()
        })
    }
}

