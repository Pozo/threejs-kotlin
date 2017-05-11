package example

import three.cameras.PerspectiveCamera
import three.geometries.BoxGeometry
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.objects.Mesh
import three.renderers.webglrenderer.WebGLRenderer
import three.scenes.Scene
import kotlin.browser.document
import kotlin.browser.window

class HelloWorld {
    val renderer: WebGLRenderer
    val scene: Scene
    val camera: PerspectiveCamera
    val cube: Mesh

    init {
        scene = Scene()
        camera = PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000)
        camera.position.z = 5.0

        renderer = WebGLRenderer()
        renderer.setSize(window.innerWidth, window.innerHeight)
        renderer.shadowMap.enabled = true
        document.body?.appendChild(renderer.domElement)

        val geometry = BoxGeometry(1, 1, 1)

        val param = MeshBasicMaterialParam()
        param.color = 0x00ff00

        val material = MeshBasicMaterial(param)

        cube = Mesh(geometry, material)
        cube.castShadow = true
        scene.add(cube)
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

