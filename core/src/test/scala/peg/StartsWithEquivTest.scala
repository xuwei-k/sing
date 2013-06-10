

// Copyright Shunsuke Sogame 2008-2013.
// Distributed under the New BSD license.


package com.github.okomoktest
package singtest; package pegtest


import com.github.okomok

import okomok.sing._
import Dense.Literal._
import junit.framework.Assert._


class StartsWithEquivTest extends org.scalatest.junit.JUnit3Suite {

    class Plus1Equiv extends AsEquiv {
        override type self = Plus1Equiv
        override  def equiv[x <: Any, y <: Any](x: x, y: y): equiv[x, y] = x.asNat.increment.equal(y.asNat)
        override type equiv[x <: Any, y <: Any]                          = x#asNat#increment#equal[y#asNat]
    }
    val Plus1Equiv = new Plus1Equiv

    def testTrivial {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = _4 :: _5 :: _6 :: Nil
        val ys: ys = _4 :: _5 :: _6 :: Nil
        type r   = peg.StartsWith.apply[xs, ys, Some[Plus1Equiv]]
        val r: r = peg.StartsWith.apply(xs, ys, Some(Plus1Equiv))
        Test.assertTrue[r#isEmpty#not]
        Test.assertSame[_3 :: _4 :: _5 :: Nil, r#get#asProduct2#_1#asList#force]
        Test.assertSame[_6 :: Nil, r#get#asProduct2#_2#asList#force]
        assertEquals(_3 :: _4 :: _5 :: Nil, r.get.asProduct2._1)
        assertEquals(_6 :: Nil, r.get.asProduct2._2)
    }

    def testNil {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = Nil
        val ys: ys = Nil
        type r   = peg.StartsWith.apply[xs, ys, Some[Plus1Equiv]]
        val r: r = peg.StartsWith.apply(xs, ys, Some(Plus1Equiv))
        Test.assertTrue[r#isEmpty#not]
        Test.assertSame[Nil, r#get#asProduct2#_1#asList#force]
        Test.assertSame[xs, r#get#asProduct2#_2#asList#force]
        assertEquals(Nil, r.get.asProduct2._1)
        assertEquals(xs, r.get.asProduct2._2)
    }

    def testNilNil {
        type xs    = Nil
        val xs: xs = Nil
        type ys    = Nil
        val ys: ys = Nil
        type r   = peg.StartsWith.apply[xs, ys, Some[Plus1Equiv]]
        val r: r = peg.StartsWith.apply(xs, ys, Some(Plus1Equiv))
        Test.assertTrue[r#isEmpty#not]
        Test.assertSame[Nil, r#get#asProduct2#_1#asList#force]
        Test.assertSame[Nil, r#get#asProduct2#_2#asList#force]
        assertEquals(Nil, r.get.asProduct2._1)
        assertEquals(Nil, r.get.asProduct2._2)
    }

    def testNone {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = _5 :: _6 :: Nil
        val ys: ys = _5 :: _6 :: Nil
        type r   = peg.StartsWith.apply[xs, ys, Some[Plus1Equiv]]
        val r: r = peg.StartsWith.apply(xs, ys, Some(Plus1Equiv))
        Test.assertTrue[r#isEmpty]
        assertEquals(None, r)
    }

    def testAll {
        type xs    = _3 :: _4 :: _5 :: _6 :: Nil
        val xs: xs = _3 :: _4 :: _5 :: _6 :: Nil
        type ys    = _4 :: _5 :: _6 :: _7 :: Nil
        val ys: ys = _4 :: _5 :: _6 :: _7 :: Nil
        type r   = peg.StartsWith.apply[xs, ys, Some[Plus1Equiv]]
        val r: r = peg.StartsWith.apply(xs, ys, Some(Plus1Equiv))
        Test.assertTrue[r#isEmpty#not]
        Test.assertSame[xs, r#get#asProduct2#_1#asList#force]
        Test.assertSame[Nil, r#get#asProduct2#_2#asList#force]
        assertEquals(xs, r.get.asProduct2._1)
        assertEquals(Nil, r.get.asProduct2._2)
    }
}
