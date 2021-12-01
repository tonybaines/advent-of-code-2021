package tonyb.day1

import java.util.Objects.nonNull

fun countDepthIncreases(depths: Sequence<Int?>): Int =
    sequenceOf(null).plus(depths).zip(depths) // Pairs of adjacent readings
        .filter { (a,b) -> nonNull(a) &&  a!! < b!! } // Find the increases
        .count()