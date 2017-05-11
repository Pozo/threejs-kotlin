package three.materials.phong

class MeshPhongMaterial(params: MeshPhongMaterialParam) : MeshPhongMaterialProxy(convert(params))

private fun convert(params: MeshPhongMaterialParam): dynamic {
    val param: dynamic = object {}

    param.color = params.color

    return params
}