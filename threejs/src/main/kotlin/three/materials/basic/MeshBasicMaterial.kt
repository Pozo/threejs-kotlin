package three.materials.basic

class MeshBasicMaterial(params: MeshBasicMaterialParam) : MeshBasicMaterialProxy(convert(params))

private fun convert(params: MeshBasicMaterialParam): dynamic {
    val param: dynamic = object {}

    param.color = params.color
    param.wireframe = params.wireframe
    param.opacity = params.opacity
    param.transparent = params.transparent
    param.visible = params.visible

    return params
}
