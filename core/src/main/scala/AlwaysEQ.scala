

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing


sealed abstract class AlwaysEQ extends AsOrdering {
    override type self = AlwaysEQ

    override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = `true`
    override type equiv[x <: Any, y <: Any]                          = `true`
    override  def compare[x <: Any, y <: Any](x: x, y: y): compare[x, y] = EQ
    override type compare[x <: Any, y <: Any]                            = EQ
}


private[sing]
object _TermOfAlwaysEQ {
    val apply: AlwaysEQ = new AlwaysEQ{}
}