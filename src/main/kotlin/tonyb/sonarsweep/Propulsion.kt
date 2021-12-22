package tonyb.sonarsweep

import java.nio.file.Files
import kotlin.io.path.Path


@JvmInline
value class Horizontal(val value: Int) {
    operator fun plus(amount: Int): Horizontal = Horizontal(value + amount)

    companion object {
        val ZERO = Horizontal(0)
    }
}

data class Position(val depth: Depth, val horizontal: Horizontal) {
    fun forward(amount: Int): Position = this.copy(horizontal = this.horizontal + amount)
    fun down(amount: Int): Position = this.copy(depth = this.depth + amount)
    fun up(amount: Int): Position = this.copy(depth = this.depth - amount) // Just assume depth will never be negative!
    fun multiplied(): Int = depth.value * horizontal.value

    companion object {
        val ZERO = Position(Depth.ZERO, Horizontal.ZERO)
    }
}

enum class Direction {
    forward, up, down;
}

data class Movement(val direction: Direction, val distance: Int) {
    companion object {
        fun fromString(s: String): Movement {
            val (direction, distance) = s.split(" ")
            return Movement(Direction.valueOf(direction), distance.toInt())
        }
    }
}

@JvmName("propulsion_by_string")
fun propulsion(movements: List<String>): Position = propulsion(movements.map { Movement.fromString(it) })

fun propulsion(movements: List<Movement>): Position = movements.fold(Position.ZERO) { acc, move ->
    when (move.direction) {
        Direction.forward -> acc.forward(move.distance)
        Direction.up -> acc.up(move.distance)
        Direction.down -> acc.down(move.distance)
    }
}

fun main() {
    val movements = Files.lines(Path("src/test/resources/day2/puzzle_input"))
        .toList()

    println(propulsion(movements).multiplied())
}