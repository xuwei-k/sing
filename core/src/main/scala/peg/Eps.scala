

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing; package peg


private[sing]
final class Eps extends PegImpl with ZeroWidth {
    type self = Eps

    override  def parse[xs <: List](xs: xs): parse[xs] = PegSuccess(Nil, xs)
    override type parse[xs <: List]                    = PegSuccess[Nil, xs]
}
