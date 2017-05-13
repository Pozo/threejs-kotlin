package three.materials.points

class PointsMaterial(params: PointsMaterialParam) : PointsMaterialProxy(convert(params))

private fun convert(params: PointsMaterialParam): dynamic {
    val param: dynamic = object {}

    param.color = params.color

    return params
}