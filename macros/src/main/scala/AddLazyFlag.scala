

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing.makro


import scala.reflect.macros.Context


object AddLazyFlag {

    def apply(c: Context)(mods: c.Modifiers): c.Modifiers = {
        import c.universe._

        val Modifiers(flags, privateWithin, annotations) = mods
        val res = Modifiers(flags | Flag.LAZY, privateWithin, annotations)

        RemoveSingmethodAnnotation(c)(res) // because scalac warns.
    }
}