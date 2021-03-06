

// Copyright Shunsuke Sogame 2008-2014.
// Distributed under the New BSD license.


package com.github.okomok.sing


object SortedMap {

    /**
     * Constructs an empty sorted map.
     */
     def empty[o <: Ordering](o: o): empty[o] = BSNil(o)
    type empty[o <: Ordering]                 = BSNil[o]


    /**
     * Constructs a one-entry sorted map.
     */
     def put[k <: Any, v <: Any](k: k, v: v): put[k, v] = empty(id(k).kind.naturalOrdering).put(k, v)
    type put[k <: Any, v <: Any]                        = empty[id[k]#kind#naturalOrdering]#put[k, v]

}
