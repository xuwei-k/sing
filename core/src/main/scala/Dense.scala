

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing


// See: http://apocalisp.wordpress.com/2010/06/24/type-level-programming-in-scala-part-5a-binary-numbers/


import _dense._


object Dense extends Literal


sealed abstract class Dense extends Nat {
    override type self <: Dense

     def head: head
    type head <: Boolean

     def tail: tail
    type tail <: Dense

     def size: size
    type size <: Peano

    override type increment <: Dense
    override type decrement <: Dense
    override type plus[that <: Nat] <: Dense
    override type minus[that <: Nat] <: Dense
    override type times[that <: Nat] <: Dense
    override type bitAnd[that <: Nat] <: Dense
    override type bitOr[that <: Nat] <: Dense

     def D_::[e <: Boolean](e: e): D_::[e]
    type D_::[e <: Boolean] <: Dense

     def shiftLeft: shiftLeft
    type shiftLeft <: Dense

     def shiftRight: shiftRight
    type shiftRight <: Dense

     def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f]
    type foldRightWithNat[z <: Any, f <: Function2] <: Any

     def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n]
    type shiftLeftBy[n <: Peano] <: Dense
}


private[sing]
sealed abstract class AsDense extends Dense with AsNat {
    override  def asDense: asDense = self
    override type asDense          = self

    override  def D_::[e <: Boolean](e: e): D_::[e] = DCons(e, self)
    override type D_::[e <: Boolean]                = DCons[e, self]

    override  def plus[that <: Nat](that: that): plus[that] = Plus.apply(self, that.asDense)
    override type plus[that <: Nat]                         = Plus.apply[self, that#asDense]

    override  def minus[that <: Nat](that: that): minus[that] = Minus.apply(self, that.asDense)
    override type minus[that <: Nat]                          = Minus.apply[self, that#asDense]

    override  def quotRem[that <: Nat](that: that): quotRem[that] = QuotRem.apply(self, that.asDense)
    override type quotRem[that <: Nat]                            = QuotRem.apply[self, that#asDense]

    override  def equal[that <: Any](that: that): equal[that] = Equal.apply(self, that.asNat.asDense)
    override type equal[that <: Any]                          = Equal.apply[self, that#asNat#asDense]

    override  def lt[that <: Nat](that: that): lt[that] = Lt.apply(self, that.asDense)
    override type lt[that <: Nat]                       = Lt.apply[self, that#asDense]

    override  def lteq[that <: Nat](that: that): lteq[that] = that.asDense.lt(self).not
    override type lteq[that <: Nat]                         = that#asDense#lt[self]#not

    override  def bitAnd[that <: Nat](that: that): bitAnd[that] = BitAnd.apply(self, that.asDense)
    override type bitAnd[that <: Nat]                           = BitAnd.apply[self, that#asDense]

    override  def bitOr[that <: Nat](that: that): bitOr[that] = BitOr.apply(self, that.asDense)
    override type bitOr[that <: Nat]                          = BitOr.apply[self, that#asDense]
}


sealed class DNil extends AsDense {
    override type self = DNil

    override  def asPeano: asPeano = Zero
    override type asPeano          = Zero

    override  def unsing: unsing = 0

    private[sing] lazy val _head = NoSuchElement("DNil.head")
    override  def head: head = _head.unwrap
    override type head       = _head.unwrap

    private[sing] lazy val _tail = NoSuchElement("DNil.tail")
    override  def tail: tail = _tail.unwrap
    override type tail       = _tail.unwrap

    override  def size: size = Zero
    override type size       = Zero

    override  def isZero: isZero = `true`
    override type isZero         = `true`

    override  def increment: increment = DCons(`true`, self)
    override type increment            = DCons[`true`, self]

    private[sing] lazy val _decrement = Unsupported("DNil.decrement")
    override  def decrement: decrement = _decrement.unwrap
    override type decrement            = _decrement.unwrap

    override  def times[that <: Nat](that: that): times[that] = self
    override type times[that <: Nat]                          = self

    override  def exp[that <: Nat](that: that): exp[that] = `if`(that.isZero, Const(Dense._1), Const(self)).apply.asNat.asDense
    override type exp[that <: Nat]                        = `if`[that#isZero, Const[Dense._1], Const[self]]#apply#asNat#asDense

    override  def shiftLeft: shiftLeft = self
    override type shiftLeft            = self

    override  def shiftRight: shiftRight = self
    override type shiftRight             = self

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = z
    override type foldRightWithNat[z <: Any, f <: Function2]                                     = z

    override  def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n] = self
    override type shiftLeftBy[n <: Peano]                       = self
}

private[sing]
object _TermOfDNil {
    val apply: DNil = new DNil
}


final case class DCons[x <: Boolean, xs <: Dense](override val head: x, override val tail: xs) extends AsDense {
    assert(head.or(tail.isZero.not))

    override type self = DCons[x, xs]

    override  def asPeano: asPeano = Succ(decrement.asPeano)
    override type asPeano          = Succ[decrement#asPeano]

    override  def unsing: unsing = (if (head.unsing) 1 else 0) + (2 * tail.unsing)

    override type head = x
    override type tail = xs

    override  val size: size = tail.size.increment
    override type size       = tail#size#increment

    override  def isZero: isZero = `false`
    override type isZero         = `false`

    override  def increment: increment = DConsIncrement.apply(head, tail)
    override type increment            = DConsIncrement.apply[head, tail]

    override  def decrement: decrement = DConsDecrement.apply(head, tail)
    override type decrement            = DConsDecrement.apply[head, tail]

    override  def times[that <: Nat](that: that): times[that] = DConsTimes.apply(head, tail, that.asDense)
    override type times[that <: Nat]                          = DConsTimes.apply[head, tail, that#asDense]

    override  def exp[that <: Nat](that: that): exp[that] = DConsExp.apply(self, that)
    override type exp[that <: Nat]                        = DConsExp.apply[self, that]

    override  def shiftLeft: shiftLeft = DCons(`false`, self)
    override type shiftLeft            = DCons[`false`, self]

    override  def shiftRight: shiftRight = tail
    override type shiftRight             = tail

    override  def foldRightWithNat[z <: Any, f <: Function2](z: z, f: f): foldRightWithNat[z, f] = f.apply(self, decrement.foldRightWithNat(z, f))
    override type foldRightWithNat[z <: Any, f <: Function2]                                     = f#apply[self, decrement#foldRightWithNat[z, f]]

    override  def shiftLeftBy[n <: Peano](n: n): shiftLeftBy[n] = DConsShiftLeftBy.apply(self, n)
    override type shiftLeftBy[n <: Peano]                       = DConsShiftLeftBy.apply[self, n]
}

