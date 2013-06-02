

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing; package peg


private[sing]
object And {
     def apply[p <: Peg](p: p): apply[p] = Impl(p)
    type apply[p <: Peg]                 = Impl[p]

    final case class Impl[p <: Peg](p: p) extends PegImpl with ZeroWidth {
        type self = Impl[p]

        override  def parse[xs <: List](xs: xs): parse[xs] = _aux(p.parse(xs), xs)
        override type parse[xs <: List]                    = _aux[p#parse[xs], xs]

        private[this]  def _aux[r <: PegResult, xs <: List](r: r, xs: xs): _aux[r, xs] =
            `if`(r.successful, Then(r, xs), const0(r)).apply.asPegResult.asInstanceOf[_aux[r, xs]]
        private[this] type _aux[r <: PegResult, xs <: List] =
            `if`[r#successful, Then[r, xs], const0[r]]#apply#asPegResult
    }

    final case class Then[r <: PegResult, xs <: List](r: r, xs: xs) extends Function0 {
        type self = Then[r, xs]
        override  def apply: apply = PegSuccess(r.get, xs)
        override type apply        = PegSuccess[r#get, xs]
    }
}
