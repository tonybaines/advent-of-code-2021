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

data class Movement(val direction: Direction, val amount: Int) {
    companion object {
        fun fromString(s: String): Movement {
            val (direction, distance) = s.split(" ")
            return Movement(Direction.valueOf(direction), distance.toInt())
        }
    }
}

@JvmName("propulsion_by_string")
fun propulsion(movements: List<String>): Position = propulsion(movements.map { Movement.fromString(it) })

private data class State(val aim: Int, val position: Position) {
    companion object {
        val ZERO = State(0, Position.ZERO)
    }
}

fun propulsion(movements: List<Movement>): Position = movements.fold(State.ZERO) { acc, move ->
    when (move.direction) {
        Direction.forward -> acc.copy(position = acc.position
            .forward(move.amount)
            .down(acc.aim * move.amount)
        )
        Direction.up -> acc.copy(aim = acc.aim - move.amount)
        Direction.down -> acc.copy(aim = acc.aim + move.amount)
    }
}.position

fun main() {
    val movements = Files.lines(Path("src/test/resources/day2/puzzle_input"))
        .toList()

    println(propulsion(movements).multiplied())
}