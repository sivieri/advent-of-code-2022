package me.sivieri.aoc2022.day9

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.findByValue
import kotlin.math.max

class LongRopeBridge(
    private val tailLength: Int,
    private var head: Coordinate2D = Coordinate2D(0, 0),
    tailInitialValue: Coordinate2D = Coordinate2D(0, 0)
) {

    private val tailsPosition: MutableMap<Int, Coordinate2D> = (1..tailLength)
        .associateWith { tailInitialValue.copy() }
        .toMutableMap()
    private val visitedTail: MutableList<Coordinate2D> = mutableListOf(tailsPosition[tailLength]!!)

    fun move(ropeMovement: RopeMovement) {
        (1..ropeMovement.steps).forEach { _ ->
            // head
            head = when (ropeMovement.ropeDirection) {
                RopeDirection.LEFT -> Coordinate2D(head.x - 1, head.y)
                RopeDirection.RIGHT -> Coordinate2D(head.x + 1, head.y)
                RopeDirection.UP -> Coordinate2D(head.x, head.y + 1)
                RopeDirection.DOWN -> Coordinate2D(head.x, head.y - 1)
            }
            // tails
            tailsPosition
                .keys
                .plus(0) // head
                .sorted()
                .zipWithNext()
                .forEach { (prev, cur) ->
                    val prevTail = if (prev == 0) head else tailsPosition[prev]!!
                    val tail = tailsPosition[cur]!!
                    when (tail.cellDistance(prevTail)) {
                        0 -> { }
                        1 -> { }
                        2 -> {
                            tailsPosition[cur] = tail.moveOneCellTowards(prevTail)
                            if (cur == tailLength) visitedTail.add(tailsPosition[cur]!!.copy())
                        }
                        else -> throw IllegalArgumentException("Tail $tail too distant from prev $prevTail!")
                    }
            }
        }
    }

    fun move(ropeMovements: List<RopeMovement>): Unit = ropeMovements.forEach { move(it) }

    fun countTailPositions(): Int = visitedTail.distinct().size

    fun printStatus() {
        val minX = tailsPosition.values.plus(head).minOf { it.x }
        val minY = tailsPosition.values.plus(head).minOf { it.y }
        val maxX = tailsPosition.values.plus(head).maxOf { it.x }
        val maxY = tailsPosition.values.plus(head).maxOf { it.y }
        val output = (maxY..minY).joinToString("\n") { y ->
            (minX..maxX).joinToString("") { x ->
                tailsPosition.findByValue(Coordinate2D(x, y))?.key?.toString() ?: "."
            }
        }
        println(output)
    }

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