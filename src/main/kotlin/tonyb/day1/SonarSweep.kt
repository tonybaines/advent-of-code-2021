package tonyb.day1

import java.nio.file.Files
import java.util.Objects.nonNull
import kotlin.io.path.Path

fun countDepthIncreases(depths: Sequence<Int?>): Int =
    countDepthIncreases(permute(depths.windowed(3) { it.sum() }))

@JvmName("countDepthIncreases_private")
private fun countDepthIncreases(depths: Sequence<Pair<Int?, Int?>>): Int =
    depths
        .filter { (a, b) -> nonNull(a) && a!! < b!! } // Find the increases
        .count()


private fun permute(depths: Sequence<Int?>) = sequenceOf(null).plus(depths).zip(depths)


private fun List<Int?>.sum() = this.reduce { a, b -> (a ?: 0) + (b ?: 0) }

fun main() {
    val depths = Files.lines(Path("src/test/resources/day1/puzzle_input"))
        .map { it.toInt() }
        .toList()

    println(countDepthIncreases(depths.asSequence()))
}
