package three.materials.meshlambert

class MeshLambertMaterial(params: MeshLambertMaterialParam) : MeshLambertMaterialProxy(convert(params))

private fun convert(params: MeshLambertMaterialParam): dynamic {
    val param: dynamic = object {}

    param.color = params.color
//    param.wireframe = params.wireframe
//    param.opacity = params.opacity
//    param.transparent = params.transparent
    param.map = params.map

    return params
}
