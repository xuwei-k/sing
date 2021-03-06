

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing


import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context


object CompileString {
    def apply(x: String): scala.Any = macro CompileStringImpl.termMacro
}


final class CompileStringImpl(override val c: Context) extends UntypedMacroImpl {
    override protected def termMacroImpl(x: c.Tree): c.Tree = c.typecheck(x)
}
