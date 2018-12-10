package examples.board

import org.w3c.dom.Node
import org.w3c.dom.events.Event
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.WheelEvent
import three.cameras.PerspectiveCamera
import kotlin.math.PI
import kotlin.math.cos

// based on https://github.com/sasoh/VillagePrototype/blob/master/js/rtsCameraControl.js
class RTSCameraControl(val camera: PerspectiveCamera, val domElement: Node) {
    val maxFov = 95f
    val minFov = 15f

    var moveSpeed: Float = 10f
    var lookSpeed: Float = 0.005f

    private var moveForward = false
    private var moveLeft = false
    private var moveBackward = false
    private var moveRight = false

    init {
        println(domElement)
        domElement.addEventListener("keydown", { event: Event ->
            if (event is KeyboardEvent) {
                when (event.keyCode) {
                    38, 87 -> moveForward = true
                    37, 65 -> moveLeft = true
                    40, 83 -> moveBackward = true
                    39, 68 -> moveRight = true
                    else -> {
                        moveForward = false
                    }
                }
            }

        }, false)
        domElement.addEventListener("keyup", { event: Event ->
            if (event is KeyboardEvent) {
                when (event.keyCode) {
                    38, 87 -> moveForward = false
                    37, 65 -> moveLeft = false
                    40, 83 -> moveBackward = false
                    39, 68 -> moveRight = false
                    else -> {
                        moveForward = false
                    }
                }
            }
        }, false)
        domElement.addEventListener("mousewheel", { event: Event ->
            if (event is WheelEvent) {
                val delta: Float = (event.deltaY / 30).toFloat()

                var currentFov = this.camera.fov.toFloat()
                currentFov += delta
                if (currentFov < this.minFov) {
                    currentFov = this.minFov
                }
                if (currentFov > this.maxFov) {
                    currentFov = this.maxFov
                }
                this.camera.fov = currentFov
                this.camera.updateProjectionMatrix()
            }

        }, false)
    }

    fun update(delta: Float) {
        val factor: Float = camera.fov.toFloat() / maxFov

        if (this.moveForward) {
            this.camera.translateZ(-this.moveSpeed * cos(this.camera.rotation.x).toFloat() * factor)
            this.camera.translateY(-this.moveSpeed * cos(PI / 2 - this.camera.rotation.x).toFloat() * factor)
        }
        if (this.moveBackward) {
            this.camera.translateZ(this.moveSpeed * cos(this.camera.rotation.x).toFloat() * factor)
            this.camera.translateY(this.moveSpeed * cos(PI / 2 - this.camera.rotation.x).toFloat() * factor)
        }
        if (this.moveLeft) {
            this.camera.translateX(-this.moveSpeed * factor)
        }
        if (this.moveRight) {
            this.camera.translateX(this.moveSpeed * factor)
        }
    }
}
