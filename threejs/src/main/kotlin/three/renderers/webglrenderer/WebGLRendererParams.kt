package three.renderers.webglrenderer

class WebGLRendererParams {
    var asDynamic: dynamic = {}

    var antialias: Boolean = false
        set(value) {
            asDynamic.antialias = value
        }
}

