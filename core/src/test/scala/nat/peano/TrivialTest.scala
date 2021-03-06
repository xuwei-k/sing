

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomoktest
package singtest; package nattest; package peanotest


import com.github.okomok

import okomok.sing._
import okomok.sing.Peano.Literal._
import okomok.sing.Peano


class TrivialTest extends org.scalatest.junit.JUnit3Suite {

    def testUnsing {
        import junit.framework.Assert._
        assertEquals(0, _0.unsing)
        assertEquals(7, _6.increment.unsing)
        assertEquals(10, _10.unsing)
        assertEquals("sing.10", _10.toString)

        assertEquals(_2, _2)
        AssertNotEquals(_2, _3)
    }

    def testIncDecDuarity {
        val x: _4 = _3.increment
        val y: _3 = _4.decrement
        ()
    }

    def testAddDuality {
        val x: _2#plus [_3] = _2 plus _3
        val y: _5 = x
        okomok.sing.assert(x equal y)
        okomok.sing.assert(x equal _5)
    }

    def testSubstractDuality {
        val x: _6# minus [_5] = _6 minus _5
        val y: _1 = x
        okomok.sing.assert(x equal y)
        okomok.sing.assert(x equal _1)
    }

    def testComparisonDuality {
        val a: _4#gt [_2] = _4 gt _2
        val b: `true` = a
        okomok.sing.assert(a equal b)
        okomok.sing.assert(a equal `true`)
    }

    trait testTrivial {
        AssertEq[scala.Int, _2#unsing]
        AssertTrue[_0# equal [_0]]

        AssertTrue[_0# nequal [_1]]
        AssertTrue[_1# nequal [_0]]

        AssertTrue[_1# equal [_1]]

        AssertTrue[_1# nequal [_2]]
        AssertTrue[_1# nequal [_3]]
        AssertTrue[_2# nequal [_1]]
        AssertTrue[_3# nequal[_1]]

        AssertTrue[_7# equal[_7]]
        AssertTrue[_2# nequal[_7]]
        AssertTrue[_7# nequal[_2]]
        AssertTrue[_6# nequal[_7]]
        AssertTrue[_7# nequal[_6]]
        AssertTrue[_0# nequal[_7]]
        AssertTrue[_7# nequal[_0]]
        AssertTrue[_1# nequal[_7]]
        AssertTrue[_7# nequal[_1]]

        AssertTrue[_1#increment# equal [_2]]
        AssertTrue[_1#increment#increment# equal [_3]]

        AssertTrue[_1#decrement# equal [_0]]
        AssertTrue[_3#decrement#decrement# equal [_1]]
        AssertTrue[_4#decrement# equal [_3]]
        AssertTrue[_7#increment#decrement#decrement# equal [_6]]
    }

    trait testAdd {
        AssertTrue[_0#plus[_0]# equal[_0]]
        AssertTrue[_0#plus[_3]# equal[_3]]
        AssertTrue[_4#plus[_3]# equal[_7]]
        AssertTrue[_1#plus[_8]# equal[_9]]
        AssertTrue[_5#plus[_2]# equal[_7]]
    }

    trait testSubtract {
        AssertTrue[_0# minus[_0]# equal[_0]]
        AssertTrue[_1# minus[_1]# equal[_0]]
        AssertTrue[_3# minus[_0]# equal[_3]]
        AssertTrue[_4# minus[_3]# equal[_1]]
        AssertTrue[_8# minus[_1]# equal[_7]]
        AssertTrue[_5# minus[_2]# equal[_3]]
        AssertTrue[_6# minus[_5]# equal[_1]]
        AssertTrue[_5# minus[_5]# equal[_0]]
    }

    trait testComparison {
        AssertTrue[_0#lt[_2]]
        AssertTrue[_3#lt[_5]]
        AssertTrue[_3#lteq[_3]]
        AssertTrue[_5#gt[_3]]
        AssertTrue[_4#gt[_0]]
        AssertTrue[_4# gteq[_2]]
        AssertTrue[_0#lteq[_0]]
        AssertTrue[_0# gteq[_0]]
        AssertFalse[_3#gt[_5]]
        AssertFalse[_0#lt[_0]]
        AssertFalse[_0#gt[_0]]
        AssertFalse[_4# gteq[_5]]
        AssertFalse[_4#lteq[_2]]
        AssertFalse[_4#lt[_4]]
        AssertFalse[_4#gt[_4]]
    }

    trait testPropagation {
        type plusPlus[n <: Peano] = n#increment#increment
        type id[n <: Peano] = n#increment#decrement
        type equaL[n <: Peano, m <: Peano] = plusPlus[n]# equal [id[m]]

        AssertTrue[plusPlus[_4]# equal [_6]]
        AssertTrue[plusPlus[_7]# equal [_9]]
        AssertTrue[id[_9]# equal [_9]]
        AssertTrue[id[_7]# equal [_7]]

        AssertTrue[equaL[_3, _5]]
        AssertTrue[equaL[_4, _6]]

        // Must work.
        type subsub[n <: Peano, m <: Peano] = n# minus[m]# minus[m]
        AssertTrue[subsub[_9, _2]# equal [_5]]
    }

}
