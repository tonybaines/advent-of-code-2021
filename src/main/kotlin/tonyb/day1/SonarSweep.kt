package tonyb.day1

import java.nio.file.Files
import kotlin.io.path.Path


@JvmInline
value class Depth(private val value: Int) {
    operator fun plus(other: Depth): Depth = Depth(value + other.value)
    operator fun compareTo(other: Depth): Int = value.compareTo(other.value)

    constructor(s: String): this(s.toInt())

    companion object {
        val NULL = Depth(-1)
    }
}

private val Depth.defined: Boolean
    get() = this != Depth.NULL

fun countDepthIncreases(depths: Sequence<Depth>): Int =
    countDepthIncreases(permute(depths.windowed(3) { it.sum() }))

private fun List<Depth>.sum() = this.reduce(Depth::plus)


@JvmName("countDepthIncreases_private")
private fun countDepthIncreases(depths: Sequence<Pair<Depth, Depth>>): Int =
    depths
        .filter { (a, b) -> a.defined && a < b } // Find the increases
        .count()


private fun permute(depths: Sequence<Depth>): Sequence<Pair<Depth, Depth>> =
    sequenceOf(Depth.NULL).plus(depths).zip(depths)


fun main() {
    val depths = Files.lines(Path("src/test/resources/day1/puzzle_input"))
        .map(::Depth)
        .toList()

    println(countDepthIncreases(depths.asSequence()))
}
