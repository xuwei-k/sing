

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing
package _set


private[sing]
object Equal {
     def apply[s <: Set, z <: Set](s: s, z: z): apply[s, z] =
        `if`(id(s).size.nequal(id(z).size), Const(`false`), Else(s, z)).apply.asBoolean
    type apply[s <: Set, z <: Set] =
        `if`[id[s]#size#nequal[id[z]#size], Const[`false`], Else[s, z]]#apply#asBoolean

    case class Else[s <: Set, z <: Set](s: s, z: z) extends AsFunction0 {
        override type self = Else[s, z]
        override  def apply: apply = id(s).asList.forall(Pred(z))
        override type apply        = id[s]#asList#forall[Pred[z]]
    }

    case class Pred[z <: Set](z: z) extends AsFunction1 {
        override type self = Pred[z]
        override  def apply[k <: Any](k: k): apply[k] = z.contains(k)
        override type apply[k <: Any]                 = z#contains[k]
    }
}
