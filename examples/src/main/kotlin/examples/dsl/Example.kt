package examples.geometry.colors

import org.khronos.webgl.Float32Array
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.Element
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import three.cameras.Camera
import three.cameras.PerspectiveCamera
import three.core.BufferAttribute
import three.geometries.IcosahedronBufferGeometry
import three.geometries.PlaneBufferGeometry
import three.materials.MaterialColors
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.materials.phong.MeshPhongMaterial
import three.materials.phong.MeshPhongMaterialParam
import three.math.Color
import three.meshBasicMaterial
import three.meshPhongMaterial
import three.renderers.webglrenderer.WebGLRenderer
import three.renderers.webglrenderer.WebGLRendererParams
import three.scene
import three.scenes.Scene
import three.textures.CanvasTexture
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.PI

class ColorsDsl {
    private val container: Element

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
        val shadowGeometry = PlaneBufferGeometry(300f, 300f, 1, 1)

        val radius = 200f

        val geometryOne = IcosahedronBufferGeometry(radius, 1)
        val count = geometryOne.attributes.position.count as Int
        geometryOne.addAttribute("color", BufferAttribute(Float32Array((count * 3)), 3))
        val geometryTwo = geometryOne.clone()
        val geometryThree = geometryOne.clone()

        val colorOne = Color()
        val positionOne = geometryOne.attributes.position
        val positionTwo = geometryTwo.attributes.position
        val positionThree = geometryThree.attributes.position

        val colors1 = geometryOne.attributes.color
        val colors2 = geometryTwo.attributes.color
        val colors3 = geometryThree.attributes.color

        for (i in 0..count) {
            colorOne.setHSL((positionOne.getY(i) / radius + 1) / 2, 1f, 0.5f)
            colors1.setXYZ(i, colorOne.r, colorOne.g, colorOne.b)

            colorOne.setHSL(0f, (positionTwo.getY(i) / radius + 1) / 2, 0.5f)
            colors2.setXYZ(i, colorOne.r, colorOne.g, colorOne.b)

            colorOne.setRGB(1, 0.8 - ((positionThree.getY(i) / radius + 1) / 2.0) as Float, 0)
            colors3.setXYZ(i, colorOne.r, colorOne.g, colorOne.b)
        }

        val pongMaterial = meshPhongMaterial {
            color = 0xffffff
            shininess = 0
            flatShading = true
            vertexColors = MaterialColors.VertexColors
        }

        val wireframeMaterial = meshBasicMaterial {
            color = 0x000000
            wireframe = true
            transparent = true
        }

        scene = scene {
            background = 0xffffff
            directionalLight {
                color = 0xffffff
                x = 0f
                y = 0f
                z = 1f
            }
            mesh {
                geometry = shadowGeometry
                material = shadowMaterial
                rotationX = -PI / 2
                y = -250.0
            }
            mesh {
                geometry = shadowGeometry
                material = shadowMaterial
                x = -400.0
                y = -250.0
                rotationX = -PI / 2
            }
            mesh {
                geometry = shadowGeometry
                material = shadowMaterial
                x = 400.0
                y = -250.0
                rotationX = -PI / 2
            }
            mesh {
                geometry = geometryOne
                material = pongMaterial
                x = -400.0
                rotationX = -1.87
                mesh {
                    geometry = geometryOne
                    material = wireframeMaterial
                }
            }
            mesh {
                geometry = geometryTwo
                material = pongMaterial
                x = 400.0
                mesh {
                    geometry = geometryTwo
                    material = wireframeMaterial
                }
            }
            mesh {
                geometry = geometryThree
                material = pongMaterial
                mesh {
                    geometry = geometryThree
                    material = wireframeMaterial
                }
            }
        }

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