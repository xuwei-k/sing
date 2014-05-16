

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing.makro


import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context


object WeakTypeOf extends DependentImpl1 {
    def apply(x: Unspecified): Unspecified = macro term_impl

    override protected def dep_term_impl(c: Context)(x: c.Tree): c.Tree = x
    override protected def dep_type_impl(c: Context)(x: c.Tree): c.Tree = {
        import c.universe._
        TypeTree(x.tpe)
    }
}
