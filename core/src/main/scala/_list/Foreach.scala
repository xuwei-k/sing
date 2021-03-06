

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing
package _list


private[sing]
object Foreach {
     def apply[xs <: List, f <: Function1](xs: xs, f: f): apply[xs, f] = `if`(xs.isEmpty, Const(Unit), Else(xs, f)).apply.asUnit
    type apply[xs <: List, f <: Function1]                             = Unit

    case class Else[xs <: List, f <: Function1](xs: xs, f: f) extends AsFunction0 {
        override type self = Else[xs, f]
        override  def apply: apply = { f.apply(xs.head); Foreach.apply(xs.tail, f) }
        override type apply = Unit
    }
}
