

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomok
package sing


trait Product2Impl extends Product2 with ProductImpl {
    final override  def asProduct2: asProduct2 = self
    final override type asProduct2             = self

    final override  def arity: arity = Peano._2
    final override type arity        = Peano._2

    final override  def productElement[n <: Nat](n: n): productElement[n] =
        `if`(n.equal(Peano._0),
            const0(_1),
            `if`(n.equal(Peano._1),
                const0(_2),
                throw0(new IndexOutOfBoundsException(n.toString))
            )
        ).apply.asInstanceOf[productElement[n]]

    final override type productElement[n <: Nat] =
        `if`[n#equal[Peano._0],
            const0[_1],
            `if`[n#equal[Peano._1],
                const0[_2],
                throw0[_]
            ]
        ]#apply

    final override  def asList: asList = _1 :: _2 :: Nil
    final override type asList         = _1 :: _2 :: Nil

    final override  def naturalOrdering: naturalOrdering = List.naturalOrdering
    final override type naturalOrdering                  = List.naturalOrdering

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product2]
}
