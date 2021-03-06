

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomoktest; package singtest; package example


import com.github.okomok.sing
import sing.Dense.Literal._


class FizzBuzz2Test extends org.scalatest.junit.JUnit3Suite {

    object Fizz extends sing.Singular {
        override type self = Fizz.type
        override def toString = "Fizz"
    }
    object Buzz extends sing.Singular {
        override type self = Buzz.type
        override def toString = "Buzz"
    }
    object FizzBuzz extends sing.Singular {
        override type self = FizzBuzz.type
        override def toString = "FizzBuzz"
    }

    implicit object _TermOfFizz extends sing._TermOf[Fizz.type] {
        override def apply = Fizz
    }
    implicit object _TermOfBuzz extends sing._TermOf[Buzz.type] {
        override def apply = Buzz
    }
    implicit object _TermOfFizzBuzz extends sing._TermOf[FizzBuzz.type] {
        override def apply = FizzBuzz
    }

    trait fizzbuzz extends sing.AsFunction1 {
        override type self = fizzbuzz
        override type apply[x <: sing.Any] =
            sing.`if`[x#asNat#rem[_15]#equal[_0],
                sing.Const[FizzBuzz.type],
                sing.`if`[x#asNat#rem[_3]#equal[_0],
                    sing.Const[Fizz.type],
                    sing.`if`[x#asNat#rem[_5]#equal[_0],
                        sing.Const[Buzz.type],
                        sing.Const[x]
                    ]
                ]
            ]#apply
    }

    def testTrivial {
        type result = sing.List.range[_1, _16]#map[fizzbuzz]#force
        assertResult(List(1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz))(sing.termOf[result].unsing)
    }
}
