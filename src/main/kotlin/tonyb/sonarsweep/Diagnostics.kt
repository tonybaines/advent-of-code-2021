package tonyb.sonarsweep

import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.math.pow

//Turn a list of binary numbers into a matrix
fun numbers(binaryNumbers: List<String>): Array<Array<Char>> =
    binaryNumbers.map { number -> number.map { it }.toTypedArray() }.toTypedArray()


fun Array<Array<Char>>.gamma(): Int {
    val numDigits = this.first().size
    return (0 until numDigits).map { pos -> mostFrequent(pos) }.asBinaryToInt()
}

fun Array<Array<Char>>.epsilon(): Int {
    val numDigits = this.first().size
    return (0 until numDigits).map { pos -> leastFrequent(pos) }.asBinaryToInt()
}

// The most-frequent value in a position
private fun Array<Array<Char>>.mostFrequent(pos: Int): Char = frequencies(pos)
    .last()
    .first

// The least-frequent value in a position
private fun Array<Array<Char>>.leastFrequent(pos: Int): Char = frequencies(pos)
    .first()
    .first

// Find the frequencies with which characters appear in teh specified position
private fun Array<Array<Char>>.frequencies(pos: Int) = map { it[pos] }
    .groupBy { it }
    .mapValues { it.value.size }
    .toList()
    .sortedBy { it.second }


private fun List<Char>.asBinaryToInt(): Int {
    val numDigits = this.size

    return (0 until numDigits)
        .map { i -> this[i].digitToInt() * 2.0.pow(numDigits - i -1) }
        .sum()
        .toInt()
}



fun main() {
    val lines = Files.lines(Path("src/test/resources/day3/puzzle_input")).toList()

    val readings = numbers(lines)

    println(readings.epsilon() * readings.gamma())
}