

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing


object Any extends AsKind {
    override  def kindId: kindId = KindId.ofAny
    override type kindId         = KindId.ofAny
}


/**
 * The sing Any
 */
trait Any extends scala.Equals {

    @Annotation.returnThis
    final val self: self = this.asInstanceOf[self]
    type self <: Any

     def kind: kind = unsupported("Any.kind")
    type kind <: Kind

    // likely to work
     def is[K <: Kind](K: K): is[K] = unsupported("Any.is")
    type is[K <: Kind] <: Boolean

    // unlikely to work
     def as[K <: Kind](K: K): as[K] = ???
    type as[K <: Kind] <: K

     def asBoolean: asBoolean = unsupported("Any.asBoolean")
    type asBoolean <: Boolean

     def asBoxed: asBoxed = unsupported("Any.asBoxed")
    type asBoxed <: Boxed

     def asEither: asEither = unsupported("Any.asBoolean")
    type asEither <: Either

     def asFunction0: asFunction0 = unsupported("Any.asFunction0")
    type asFunction0 <: Function0

     def asFunction1: asFunction1 = unsupported("Any.asFunction1")
    type asFunction1 <: Function1

     def asFunction2: asFunction2 = unsupported("Any.asFunction2")
    type asFunction2 <: Function2

     def asFunction3: asFunction3 = unsupported("Any.asFunction3")
    type asFunction3 <: Function3

     def asPartialFunction: asPartialFunction = unsupported("Any.asPartialFunction")
    type asPartialFunction <: PartialFunction

     def asKind: asKind = unsupported("Any.asKind")
    type asKind <: Kind

     def asList: asList = unsupported("Any.asList")
    type asList <: List

     def asMap: asMap = unsupported("Any.asMap")
    type asMap <: Map

     def asNat: asNat = unsupported("Any.asNat")
    type asNat <: Nat

     def asSet: asSet = unsupported("Any.asSet")
    type asSet <: Set

     def asOption: asOption = unsupported("Any.asOption")
    type asOption <: Option

     def asRelation: asRelation = unsupported("Any.asRelation")
    type asRelation <: Relation

     def asEquiv: asEquiv = unsupported("Any.asEquiv")
    type asEquiv <: Equiv

     def asPartialOrdering: asPartialOrdering = unsupported("Any.asPartialOrdering")
    type asPartialOrdering <: PartialOrdering

     def asOrdering: asOrdering = unsupported("Any.asOrdering")
    type asOrdering <: Ordering

     def asOrderingResult: asOrderingResult = unsupported("Any.asOrderingResult")
    type asOrderingResult <: OrderingResult

     def asProduct: asProduct = unsupported("Any.asProduct")
    type asProduct <: Product

     def asProduct1: asProduct1 = unsupported("Any.asProduct1")
    type asProduct1 <: Product1

     def asProduct2: asProduct2 = unsupported("Any.asProduct2")
    type asProduct2 <: Product2

     def asProduct3: asProduct3 = unsupported("Any.asProduct3")
    type asProduct3 <: Product3

     def asUnit: asUnit = unsupported("Any.asUnit")
    type asUnit <: Unit

     def equal[that <: Any](that: that): equal[that] = unsupported("Any.equal")
    type equal[that <: Any] <: Boolean

     def nequal[that <: Any](that: that): nequal[that] = unsupported("Any.nequal")
    type nequal[that <: Any] <: Boolean

    /**
     * Escapes from the sing world.
     */
     def unsing: unsing = unsupported("Any.unsing")
    type unsing

    final protected def refEquals(that: scala.Any) = super.equals(that)
    final protected def refHashCode = super.hashCode
    final protected def refToString = super.toString
}


trait AsAny extends AnyImpl {
    override  def kind: kind = Any
    override type kind       = Any.type
}


trait AnyImpl extends Any {
    override  def is[K <: Kind](K: K): is[K] = kind.conformsTo(K)
    override type is[K <: Kind]              = kind#conformsTo[K]

    override  def nequal[that <: Any](that: that): nequal[that] = equal(that).not
    override type nequal[that <: Any]                           = equal[that]#not
}
