

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing.makro


import scala.language.experimental.macros
import scala.reflect.macros.Context


trait Assert1Impl {
    protected def impl(c: Context)(x: c.Type): Unit

    final def term_impl_[x](c: Context)(implicit tx: c.WeakTypeTag[x]): c.Expr[Unit] = {
        import c.universe._

        _impl(c)(tx)
        reify(())
    }

    final def term_impl[x](c: Context)(x: c.Expr[x])(implicit tx: c.WeakTypeTag[x]): c.Expr[Unit] = {
        import c.universe._

        _impl(c)(tx)
        reify(())
    }

    final def type_impl[x](c: Context)(implicit tx: c.WeakTypeTag[x]): c.Tree = {
        import c.universe._

        _impl(c)(tx)
        tq"scala.Unit"
    }

    private def _impl[x](c: Context)(tx: c.WeakTypeTag[x]): Unit = {
        import c.universe._

        val x = weakTypeOf(tx)
        impl(c)(x)
    }
}