package three.materials.linebasic

class LineBasicMaterial(params: LineBasicMaterialParam) : LineBasicMaterialProxy(convert(params))

private fun convert(params: LineBasicMaterialParam): dynamic {
    val param: dynamic = object {}

    param.color = params.color
    param.wireframe = params.wireframe

    return params
}
