# threejs-kotlin
Recently I started learn Kotlin js and based on [Nicolas article](https://blog.frankel.ch/kotlin-front-end-developers/) I've wanted to try out Kotlin tarnspiling ability. 

This is a Three.js bindings for Kotlin using external modifiers and based on Three.js [r84](https://github.com/mrdoob/three.js/releases/tag/r84) with wroking examples.

<p align="center"><img src="https://github.com/Pozo/threejs-kotlin/blob/master/hello-world.gif" alt="amf loader"></p>
<p align="center"><img src="https://github.com/Pozo/threejs-kotlin/blob/master/amf-loader.gif" alt="amf loader"></p>

## Kotlin JS and javascript modules

If you would like to use any javascript library with Kotlin, you can try several ways.
According to the [Kotlin Language Documentation](https://kotlinlang.org/docs/reference/): 

#### ts2kt
*"To access third-party frameworks with a strongly-typed API, you can convert TypeScript definitions from the Definitely Typed type definitions repository to Kotlin using the [ts2kt](https://github.com/Kotlin/ts2kt) tool."*

With [ts2kt](https://github.com/Kotlin/ts2kt) you can try out the following libraries:
 - [DefinitelyTyped/types/three](https://github.com/DefinitelyTyped/DefinitelyTyped/tree/c91f8ff368ad9f89d2d85511168486247fd93c6e/types/three)
 - [kontan/three.d.ts](https://github.com/kontan/three.d.ts)
 - [selbyk/three.ts](https://github.com/selbyk/three.ts)
 
#### external modifier

*"To tell Kotlin that a certain declaration is written in pure JavaScript, you should mark it with external modifier. When the compiler
sees such a declaration, it assumes that the implementation for the corresponding class, function or property is provided by the
developer, and therefore does not try to generate any JavaScript code from the declaration. This means that you should omit
bodies of external declarations."*

There are a few existing solutions based on this method.

- [ClassicThunder/Kotlin_ThreeJS](https://github.com/ClassicThunder/Kotlin_ThreeJS)
- [michael-johansen/kotlin-three.js](https://github.com/michael-johansen/kotlin-three.js)

## Usage

    git clone https://github.com/Pozo/threejs-kotlin
    cd threejs-kotlin
    ./gradlew examples:run
    xdg-open http://localhost:8088

##### Goals

 - strongly-typed API
 
  
more details later, play with example and discover the code! :smiley_cat:

## Licensing

Please see the file called LICENSE.

## Links
##### Kotlin related

 - [Kotlin JavaScript overview](http://kotlinlang.org/docs/reference/js-overview.html)
 - [Kotlin JavaScript modules](https://kotlinlang.org/docs/reference/js-modules.html)
 - [kotlinjs-frontend-plugin](https://github.com/Kotlin/kotlin-frontend-plugin)
 - [Nicolas Fränkel - Creating a simple Google Maps using Kotlin (an example using external modifier)](https://blog.frankel.ch/kotlin-front-end-developers)

##### Three.js

 - [Three.js homepage](https://threejs.org/docs/)
 - [Three.js repository](https://github.com/mrdoob/three.js/)
 - [Three.js hello-world (codepen)](http://codepen.io/anon/pen/Wjzjdo?editors=0010)

## Contact

  Zoltan Polgar - pozo@gmx.com
  
  Please do not hesitate to contact me if you have any further questions. 
