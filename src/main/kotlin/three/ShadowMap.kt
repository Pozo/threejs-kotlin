package three

//src/constants.js
enum class ShadowMap(val value: Int) {
    BasicShadowMap(0),
    PCFShadowMap(1),
    PCFSoftShadowMap(2)
}

//src/constants.js
enum class Shading(val value: Int) {
    FlatShading(1),
    SmoothShading(2)
}
