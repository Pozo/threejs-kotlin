package three.renderers.webglrenderer

class WebGLRenderer : WebGLRendererProxy {
    constructor() : super()
    constructor(params: WebGLRendererParams) : super(parse(params))
}

fun parse(params: WebGLRendererParams): dynamic {
    val param: dynamic = object {}

    param.antialias = params.antialias

    return params
}

