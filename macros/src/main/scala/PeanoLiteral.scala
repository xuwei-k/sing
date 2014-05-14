

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing.makro


import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context


object PeanoLiteral extends TypedDependentImpl1[Int] {
    def apply(x: Int): Unspecified = macro dep_impl

    override protected def dep_extract(c: Context)(x: c.Tree): Int = ExtractNat(c)(x)

    // 2 --> Succ(Succ(Zero))
    override protected def dep_term_impl(c: Context)(x: Int): c.Tree = {
        import c.universe._

        val zero: c.Tree = q"${sing_(c)}.Zero"
        val succ: c.Tree = q"${sing_(c)}.Succ"

        (0 until x).foldRight(zero) { (_, res) =>
            Apply(succ, List(res))
        }
    }

    override protected def dep_type_impl(c: Context)(x: Int): c.Tree = {
        import c.universe._

        val zero: c.Tree = tq"${sing_(c)}.Zero"
        val succ: c.Tree = tq"${sing_(c)}.Succ"

        (0 until x).foldRight(zero) { (_, res) =>
            AppliedTypeTree(succ, List(res))
        }
    }
}
