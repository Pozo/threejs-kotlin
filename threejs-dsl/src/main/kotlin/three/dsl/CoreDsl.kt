package three

import three.core.Object3D
import three.lights.DirectionalLight
import three.materials.Material
import three.materials.MaterialColors
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.materials.phong.MeshPhongMaterial
import three.materials.phong.MeshPhongMaterialParam
import three.math.Color
import three.objects.Mesh
import three.scenes.Scene

@DslMarker
annotation class ThreejsDSL

interface Object3DBuilder {

    fun build(): Object3D
}

@ThreejsDSL
class MeshBuilder : Object3DBuilder {

    val objects = mutableListOf<Object3D>()

    var geometry: dynamic = null
    var material: dynamic = null
    var x: Double? = null
    var y: Double? = null

    var rotationX: Double? = null

    override fun build(): Object3D {
        val mesh = Mesh(geometry, material)

        x?.apply { mesh.position.x = x as Double }
        y?.apply { mesh.position.y = y as Double }
        rotationX?.apply { mesh.rotation.x = rotationX as Double }

        for (`object` in objects) {
            mesh.add(`object`)
        }

        return mesh
    }

    fun mesh(setup: MeshBuilder.() -> Unit = {}) {
        val meshBuilder = MeshBuilder()
        meshBuilder.setup()
        objects += meshBuilder.build()
    }

}

@ThreejsDSL
class SceneBuilder {

    val objects = mutableListOf<Object3D>()

    var background: Int = 0

    operator fun Object3D.unaryPlus() {
        objects += this
    }

    fun build(): Scene {
        val scene = Scene()
        scene.background = Color(background)

        for (`object` in objects) {
            scene.add(`object`)
        }

        return scene
    }

    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR, message = "Scene can't be nested.")
    fun scene(param: () -> Unit = {}) {
    }

    fun mesh(setup: MeshBuilder.() -> Unit = {}) {
        val meshBuilder = MeshBuilder()
        meshBuilder.setup()
        objects += meshBuilder.build()
    }

    fun directionalLight(setup: DirectionalLightBuilder.() -> Unit = {}) {
        val meshBuilder = DirectionalLightBuilder()
        meshBuilder.setup()
        objects += meshBuilder.build()
    }

}

@ThreejsDSL
class DirectionalLightBuilder : Object3DBuilder {

    var color: Int? = null
    var intensity: Float? = null

    var x: Float = 0f
    var y: Float = 0f
    var z: Float = 0f

    override fun build(): Object3D {
        val mesh = DirectionalLight()

        color?.apply { mesh.color = Color(color as Int) }
        intensity?.apply { mesh.intensity = intensity as Float }
        mesh.position.set(x, y, z)

        return mesh
    }

    @Suppress("UNUSED_PARAMETER")
    @Deprecated(level = DeprecationLevel.ERROR, message = "Directional Light can't be nested.")
    fun directionalLight(param: () -> Unit = {}) {
    }

}

fun scene(setup: SceneBuilder.() -> Unit): Scene {
    val sectionBuilder = SceneBuilder()
    sectionBuilder.setup()
    return sectionBuilder.build()
}
