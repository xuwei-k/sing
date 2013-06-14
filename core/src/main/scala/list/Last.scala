

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing; package list


private[sing]
object Last {
     def apply[xs <: List](xs: xs): apply[xs] = `if`(id(xs).tail.isEmpty, Const(id(xs).head), Else(xs)).apply
    type apply[xs <: List]                    = `if`[id[xs]#tail#isEmpty, Const[id[xs]#head], Else[xs]]#apply

    case class Else[xs <: List](xs: xs) extends AsFunction0 {
        override type self = Else[xs]
        override  def apply: apply = Last.apply(id(xs).tail)
        override type apply        = Last.apply[id[xs]#tail]
    }
}
