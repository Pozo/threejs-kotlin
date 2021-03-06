package examples

import three.Shading
import three.ShadowMap
import three.cameras.PerspectiveCamera
import three.geometries.BoxGeometry
import three.geometries.PlaneGeometry
import three.lights.PointLight
import three.materials.phong.MeshPhongMaterial
import three.materials.phong.MeshPhongMaterialParam
import three.objects.Mesh
import three.renderers.webglrenderer.WebGLRenderer
import three.renderers.webglrenderer.WebGLRendererParams
import three.scenes.Scene
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI

class HelloWorldShadowWithPlane {
    val renderer: WebGLRenderer
    val scene: Scene
    val camera: PerspectiveCamera
    val cube: Mesh

    init {
        scene = Scene()
        camera = PerspectiveCamera(75, (window.innerWidth / window.innerHeight).toDouble(), 0.1, 1000)

        val params = WebGLRendererParams()
        params.antialias = true
        renderer = WebGLRenderer(params)

        renderer.setSize(window.innerWidth, window.innerHeight)
        renderer.shadowMap.enabled = true
        renderer.shadowMap.type = ShadowMap.PCFSoftShadowMap.value
        document.body?.appendChild(renderer.domElement)

        val light = createLight()
        scene.add(light)

        cube = createCube()
        scene.add(cube)

        val plane = createPlane()
        scene.add(plane)

        plane.position.y = -2.0
        plane.rotation.x = - PI / 2

        camera.position.z = 5.0
        camera.position.y = 2.0
        camera.lookAt(cube.position)
    }

    private fun createPlane(): Mesh {
        val planeGeometry = PlaneGeometry(20, 20, 32, 32)

        val param = MeshPhongMaterialParam()

        param.color = 0x00dddd
        param.specular = 0x009900
        param.shininess = 10
        param.shading = Shading.FlatShading.value

        val planeMaterial = MeshPhongMaterial(param)

        val plane = Mesh(planeGeometry, planeMaterial)
        plane.receiveShadow = true

        return plane
    }

    private fun createCube(): Mesh {
        val geometry = BoxGeometry(1, 1, 1)

        val param = createPhongMaterialParam()
        val material = MeshPhongMaterial(param)

        val mesh = Mesh(geometry, material)
        mesh.castShadow = true

        return mesh
    }

    private fun createLight(): PointLight {
        val light = PointLight(0xffffff, 1.0, 100)
        light.position.set(0f, 8f, 0f)
        light.castShadow = true
        light.shadow.mapSize.width = 1024f
        light.shadow.mapSize.height = 1024f
        return light
    }

    private fun createPhongMaterialParam(): MeshPhongMaterialParam {
        val param = MeshPhongMaterialParam()

        param.color = 0xdddddd
        param.specular = 0x999999
        param.shininess = 15
        param.shading = Shading.FlatShading.value

        return param
    }

    fun render() {
        window.requestAnimationFrame {
            cube.rotation.x += 0.01
            cube.rotation.y += 0.01
            render()
        }
        renderer.render(scene, camera)
    }
}

