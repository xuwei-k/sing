

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing


sealed abstract class OrderingResult extends Any {
    override type self <: OrderingResult
    override type unsing = scala.Int

     def isLT: isLT
    type isLT <: Boolean
     def isGT: isGT
    type isGT <: Boolean
     def isEQ: isEQ
    type isEQ <: Boolean

     def isLTEQ: isLTEQ
    type isLTEQ <: Boolean
     def isGTEQ: isGTEQ
    type isGTEQ <: Boolean
}


private[sing]
sealed abstract class AsOrderingResult extends OrderingResult with AsAny {
    override  def asOrderingResult: asOrderingResult = self
    override type asOrderingResult                   = self

    override  def isLTEQ: isLTEQ = isLT.or(isEQ)
    override type isLTEQ         = isLT#or[isEQ]
    override  def isGTEQ: isGTEQ = isGT.or(isEQ)
    override type isGTEQ         = isGT#or[isEQ]

    override def canEqual(that: scala.Any) = that.isInstanceOf[OrderingResult]
}


sealed abstract class LT extends AsOrderingResult {
    override type self = LT

    override  def unsing: unsing = -1

    override  def equal[that <: Any](that: that): equal[that] = that.asOrderingResult.isLT
    override type equal[that <: Any]                          = that#asOrderingResult#isLT

    override  def isLT: isLT = `true`
    override type isLT       = `true`
    override  def isGT: isGT = `false`
    override type isGT       = `false`
    override  def isEQ: isEQ = `false`
    override type isEQ       = `false`
}

private[sing]
object _TermOfLT {
    val apply: LT = new LT{}
}


sealed abstract class GT extends AsOrderingResult {
    override type self = GT

    override  def unsing: unsing = 1

    override  def equal[that <: Any](that: that): equal[that] = that.asOrderingResult.isGT
    override type equal[that <: Any]                          = that#asOrderingResult#isGT

    override  def isLT: isLT = `false`
    override type isLT       = `false`
    override  def isGT: isGT = `true`
    override type isGT       = `true`
    override  def isEQ: isEQ = `false`
    override type isEQ       = `false`
}

private[sing]
object _TermOfGT{
    val apply: GT = new GT{}
}


sealed abstract class EQ extends AsOrderingResult {
    override type self = EQ

    override  def unsing: unsing = 0

    override  def equal[that <: Any](that: that): equal[that] = that.asOrderingResult.isEQ
    override type equal[that <: Any]                          = that#asOrderingResult#isEQ

    override  def isLT: isLT = `false`
    override type isLT       = `false`
    override  def isGT: isGT = `false`
    override type isGT       = `false`
    override  def isEQ: isEQ = `true`
    override type isEQ       = `true`
}

private[sing]
object _TermOfEQ {
    val apply: EQ = new EQ{}
}
