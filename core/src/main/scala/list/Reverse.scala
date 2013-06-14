

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing; package list


private[sing]
object ReverseAppend {
     def apply[xs <: List, ys <: List](xs: xs, ys: ys): apply[xs, ys] =
        `if`(xs.isEmpty, Const(ys), Else(xs, ys)).apply.asList
    type apply[xs <: List, ys <: List] =
        `if`[xs#isEmpty, Const[ys], Else[xs, ys]]#apply#asList

    case class Else[xs <: List, ys <: List](xs: xs, ys: ys) extends AsFunction0 {
        override type self = Else[xs, ys]
        override  def apply: apply = ReverseAppend.apply(xs.tail, Cons(xs.head, ys))
        override type apply        = ReverseAppend.apply[xs#tail, Cons[xs#head, ys]]
    }
}