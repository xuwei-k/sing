

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing
package _dense


private[sing]
object DConsDecrement {
     def apply[x <: Boolean, xs <: Dense](x: x, xs: xs): apply[x, xs] =
        `if`(xs.isZero, Const(xs), `if`(x, Then(x, xs), Else(x, xs))).apply.asNat.asDense
    type apply[x <: Boolean, xs <: Dense] =
        `if`[xs#isZero, Const[xs], `if`[x, Then[x, xs], Else[x, xs]]]#apply#asNat#asDense

    // (`true` :: xs).decrement
    case class Then[x <: Boolean, xs <: Dense](x: x, xs: xs) extends AsFunction0 {
        override type self = Then[x, xs]
        override  def apply: apply = DCons(`false`, xs)
        override type apply        = DCons[`false`, xs]
    }

    // (`false` :: xs).decrement
    case class Else[x <: Boolean, xs <: Dense](x: x, xs: xs) extends AsFunction0 {
        override type self = Else[x, xs]
        override  def apply: apply = DCons(`true`, xs.decrement)
        override type apply        = DCons[`true`, xs#decrement]
    }
}
