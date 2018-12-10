package three

import three.materials.Material
import three.materials.MaterialColors
import three.materials.basic.MeshBasicMaterial
import three.materials.basic.MeshBasicMaterialParam
import three.materials.phong.MeshPhongMaterial
import three.materials.phong.MeshPhongMaterialParam

interface MaterialBuilder {

    fun build(): Material
}

@ThreejsDSL
class MeshPhongMaterialBuilder : MaterialBuilder {

    var color: Int? = null

    var shininess: Int? = null

    var flatShading: Boolean? = null

    var vertexColors: MaterialColors? = null

    override fun build(): MeshPhongMaterial {
        val phongParam = MeshPhongMaterialParam()

        color?.apply { phongParam.color = color as Int }
        shininess?.apply { phongParam.shininess = shininess as Int }
        flatShading?.apply { phongParam.flatShading = flatShading as Boolean }
        vertexColors?.apply { phongParam.vertexColors = vertexColors as MaterialColors }

        return MeshPhongMaterial(phongParam)
    }
}

@ThreejsDSL
class MeshBasicMaterialBuilder : MaterialBuilder {

    var color: Int? = null

    var wireframe: Boolean? = null

    var transparent: Boolean? = null

    override fun build(): MeshBasicMaterial {
        val materialParam = MeshBasicMaterialParam()

        color?.apply { materialParam.color = color as Int }
        wireframe?.apply { materialParam.wireframe = wireframe as Boolean }
        transparent?.apply { materialParam.transparent = wireframe as Boolean }

        return MeshBasicMaterial(materialParam)
    }
}

fun meshPhongMaterial(setup: MeshPhongMaterialBuilder.() -> Unit): MeshPhongMaterial {
    val sectionBuilder = MeshPhongMaterialBuilder()
    sectionBuilder.setup()
    return sectionBuilder.build()
}

fun meshBasicMaterial(setup: MeshBasicMaterialBuilder.() -> Unit): MeshBasicMaterial {
    val sectionBuilder = MeshBasicMaterialBuilder()
    sectionBuilder.setup()
    return sectionBuilder.build()
}