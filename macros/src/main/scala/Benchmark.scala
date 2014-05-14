

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing.makro


import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context


object Benchmark extends UntypedImpl {
    def apply(x: String): Unspecified = macro untyped_impl

    override protected def untyped_impl_impl(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._

        val start = System.currentTimeMillis
        c.typecheck(x)
        val elapsed = System.currentTimeMillis - start
        q"$elapsed"
    }
}
