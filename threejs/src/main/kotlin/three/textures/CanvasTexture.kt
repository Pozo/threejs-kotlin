@file:JsQualifier("THREE")

package three.textures

import org.w3c.dom.HTMLElement

@JsName("CanvasTexture")
external class CanvasTexture : Texture {

    constructor(canvas: HTMLElement = definedExternally,
                mapping: dynamic = definedExternally,
                wrapS: dynamic = definedExternally,
                wrapT: dynamic = definedExternally,
                magFilter: dynamic = definedExternally,
                minFilter: dynamic = definedExternally,
                format: dynamic = definedExternally,
                type: dynamic = definedExternally,
                anisotropy: Number = definedExternally)

}
