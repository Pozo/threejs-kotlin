package three.renderers.webglrenderer

class WebGLRenderer : WebGLRendererProxy {
    constructor() : super()
    constructor(params: WebGLRendererParams) : super(params.asDynamic)
}
