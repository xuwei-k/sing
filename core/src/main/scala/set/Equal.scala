

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing; package set


private[sing]
object Equal {
     def apply[s <: Set, z <: Set](s: s, z: z): apply[s, z] =
        `if`(s.size.nequal(z.size), Const(`false`), Else(s, z)).apply.asBoolean.asInstanceOf[apply[s, z]]
    type apply[s <: Set, z <: Set] =
        `if`[s#size#nequal[z#size], Const[`false`], Else[s, z]]#apply#asBoolean

    case class Else[s <: Set, z <: Set](s: s, z: z) extends AsFunction0 {
        override type self = Else[s, z]
        override  def apply: apply = s.asList.forall(Pred(z))
        override type apply        = s#asList#forall[Pred[z]]
    }

    case class Pred[z <: Set](z: z) extends AsFunction1 {
        override type self = Pred[z]
        override  def apply[k <: Any](k: k): apply[k] = z.contains(k)
        override type apply[k <: Any]                 = z#contains[k]
    }
}
