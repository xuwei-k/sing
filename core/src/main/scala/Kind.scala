

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing


object Kind extends AsKind {
    override  def kindId: kindId = KindId.ofKind
    override type kindId         = KindId.ofKind
}


trait Kind extends Any {
    override type self <: Kind

    /**
     * ID number
     */
     def kindId: kindId = unsupported("Kind.kindId")
    type kindId <: Nat

    /**
     * Checks the kind-conformance.
     */
     def conformsTo[that <: Kind](that: that): conformsTo[that] = unsupported("Kind.conformsTo")
    type conformsTo[that <: Kind] <: Boolean

    /**
     * Returns the natural ordering.
     */
     def naturalOrdering: naturalOrdering = unsupported("Kind.naturalOrdering")
    type naturalOrdering <: Ordering
}


trait AsKind extends KindImpl {
    override type self = this.type // A `Kind` is usually an `object`.

    override  def kind: kind = Kind
    override type kind       = Kind.type
}


trait KindImpl extends Kind with AnyImpl with RefEquals {
    override  def unsing: unsing = kindId.unsing
    override type unsing         = kindId#unsing

    override  def conformsTo[that <: Kind](that: that): conformsTo[that] = id(kindId).bitAnd(id(that).kindId).equal(id(that).kindId)
    override type conformsTo[that <: Kind]                               = id[kindId]#bitAnd[id[that]#kindId]#equal[id[that]#kindId]

    override def canEqual(that: scala.Any) = that.isInstanceOf[Kind]
}