

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing
package _dense


private[sing]
object Minus {
     def apply[xs <: Dense, ys <: Dense](xs: xs, ys: ys): apply[xs, ys] =
        Match(xs, ys,
            Const(DNil),
            Throw(new UnsupportedOperationException("sing.DNil.minus positive")),
            Const(xs),
            DConsMatch(xs, ys,
                CaseXX(xs, ys),
                CaseTF(xs, ys),
                CaseFT(xs, ys),
                CaseXX(xs, ys)
            )
        ).apply.asNat.asDense

    type apply[xs <: Dense, ys <: Dense] =
        Match[xs, ys,
            Const[DNil],
            Throw,
            Const[xs],
            DConsMatch[xs, ys,
                CaseXX[xs, ys],
                CaseTF[xs, ys],
                CaseFT[xs, ys],
                CaseXX[xs, ys]
            ]
        ]#apply#asNat#asDense

    case class CaseXX[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends AsFunction0 {
        override type self = CaseXX[xs, ys]
        override  def apply: apply = id(xs).tail.minus(id(ys).tail).shiftLeft
        override type apply        = id[xs]#tail#minus[id[ys]#tail]#shiftLeft
    }

    case class CaseTF[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends AsFunction0 {
        override type self = CaseTF[xs, ys]
        private[this]   def xst_sub_yst: xst_sub_yst = id(xs).tail.minus(id(ys).tail)
        private[this]  type xst_sub_yst              = id[xs]#tail#minus[id[ys]#tail]
        override  def apply: apply = DCons(`true`, xst_sub_yst)
        override type apply        = DCons[`true`, xst_sub_yst]
    }

    case class CaseFT[xs <: Dense, ys <: Dense](xs: xs, ys: ys) extends AsFunction0 {
        override type self = CaseFT[xs, ys]
        override  def apply: apply = DCons(`true`, id(xs).tail.decrement.minus(id(ys).tail))
        override type apply        = DCons[`true`, id[xs]#tail#decrement#minus[id[ys]#tail]]
    }
}
