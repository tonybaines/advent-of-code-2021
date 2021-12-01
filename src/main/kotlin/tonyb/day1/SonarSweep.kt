package tonyb.day1

import java.nio.file.Files
import java.util.Objects.nonNull
import kotlin.io.path.Path

fun countDepthIncreases(depths: Sequence<Int?>): Int =
    sequenceOf(null).plus(depths).zip(depths) // Pairs of adjacent readings
        .filter { (a,b) -> nonNull(a) &&  a!! < b!! } // Find the increases
        .count()

fun main() {
    val depths = Files.lines(Path("src/test/resources/day1/puzzle_input"))
        .map { it.toInt() }
        .toList()

    println(countDepthIncreases(depths.asSequence()))
}