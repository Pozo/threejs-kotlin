@file:JsQualifier("THREE")

package examples.loader.amf

import three.core.Object3D

//examples/js/loaders/AMFLoader.js
@JsName("AMFLoader")
external class AMFLoader {
    fun load(url: String, function: ((Object3D) -> dynamic)?)
}