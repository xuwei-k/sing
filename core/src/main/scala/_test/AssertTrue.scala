

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing; package _test


import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import makro._


private[sing]
object AssertTrue extends AssertImpl1 {
    override protected def assert_type_only(c: Context)(x: c.Type): AssertResult = {
        import c.universe._
        if (x =:= weakTypeOf[`true`]) {
            AssertSuccess
        } else {
            AssertFailure(show(x.dealias) + " required to be `true`.")
        }
    }
}


private[sing]
object AssertFalse extends AssertImpl1 {
    override protected def assert_type_only(c: Context)(x: c.Type): AssertResult = {
        import c.universe._
        if (x =:= weakTypeOf[`false`]) {
            AssertSuccess
        } else {
            AssertFailure(show(x.dealias) + " required to be `false`.")
        }
    }
}
