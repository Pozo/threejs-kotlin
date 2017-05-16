@file:JsQualifier("THREE")

package three.loaders

import org.w3c.xhr.XMLHttpRequest
import three.textures.Texture

@JsName("TextureLoader")
external class TextureLoader {
    fun load(url: String): Texture

    fun load(url: String,
             onLoad: ((Texture) -> dynamic)?,
             onProgress: ((XMLHttpRequest) -> dynamic)?,
             onError: ((Unit) -> dynamic)?): Texture
}
