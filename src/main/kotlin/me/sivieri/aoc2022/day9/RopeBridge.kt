package me.sivieri.aoc2022.day9

import me.sivieri.aoc2022.common.Coordinate2D
import kotlin.math.max

class RopeBridge(
    var head: Coordinate2D = Coordinate2D(0, 0),
    var tail: Coordinate2D = Coordinate2D(0, 0)
) {

    val visitedTail: MutableList<Coordinate2D> = mutableListOf(tail.copy())

    init {
        assert(tail.cellDistance(head) <= 2)
    }

    fun move(ropeMovement: RopeMovement) {
        (1..ropeMovement.steps).forEach { _ ->
            // head
            head = when (ropeMovement.ropeDirection) {
                RopeDirection.LEFT -> Coordinate2D(head.x - 1, head.y)
                RopeDirection.RIGHT -> Coordinate2D(head.x + 1, head.y)
                RopeDirection.UP -> Coordinate2D(head.x, head.y + 1)
                RopeDirection.DOWN -> Coordinate2D(head.x, head.y - 1)
            }
            // tail
            when (tail.cellDistance(head)) {
                0 -> { }
                1 -> { }
                2 -> {
                    tail = tail.moveOneCellTowards(head)
                    visitedTail.add(tail.copy())
                }
                else -> throw IllegalArgumentException("Tail $tail too distant from head $head!")
            }
        }
    }

    fun move(ropeMovements: List<RopeMovement>): Unit = ropeMovements.forEach { move(it) }

    fun countTailPositions(): Int = visitedTail.distinct().size

    companion object {
        fun Coordinate2D.cellDistance(other: Coordinate2D): Int = when {
            this.x == other.x -> kotlin.math.abs(this.y - other.y)
            this.y == other.y -> kotlin.math.abs(this.x - other.x)
            else -> max(kotlin.math.abs(this.y - other.y), kotlin.math.abs(this.x - other.x))
        }

        fun Coordinate2D.moveOneCellTowards(other: Coordinate2D): Coordinate2D = when {
            // vertical distance
            this.x == other.x && this.y > other.y -> Coordinate2D(this.x, this.y - 1)
            this.x == other.x && this.y < other.y -> Coordinate2D(this.x, this.y + 1)
            // horizontal distance
            this.x > other.x && this.y == other.y -> Coordinate2D(this.x - 1, this.y)
            this.x < other.x && this.y == other.y -> Coordinate2D(this.x + 1, this.y)
            // diagonal distance
            this.x > other.x && this.y > other.y -> Coordinate2D(this.x - 1, this.y - 1)
            this.x < other.x && this.y < other.y -> Coordinate2D(this.x + 1, this.y + 1)
            this.x > other.x && this.y < other.y -> Coordinate2D(this.x - 1, this.y + 1)
            this.x < other.x && this.y > other.y -> Coordinate2D(this.x + 1, this.y - 1)
            // same place
            else -> this
        }
    }

}