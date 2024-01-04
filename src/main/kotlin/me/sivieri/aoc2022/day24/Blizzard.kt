package me.sivieri.aoc2022.day24

import me.sivieri.aoc2022.common.Coordinate2D

data class Blizzard(
    val position: Coordinate2D,
    val direction: BlizzardDirection,
    private val xmin: Int,
    private val ymin: Int,
    private val xmax: Int,
    private val ymax: Int
) {
    val nextPosition: Coordinate2D = generateNextPosition()

    fun move(): Blizzard = Blizzard(nextPosition, direction, xmin, ymin, xmax, ymax)

    private fun generateNextPosition() = when (direction) {
        BlizzardDirection.UP -> {
            val y = if (position.y - 1 < ymin) ymax - 1
            else position.y - 1
            Coordinate2D(position.x, y)
        }
        BlizzardDirection.DOWN -> {
            val y = if (position.y + 1 == ymax) ymin
            else position.y + 1
            Coordinate2D(position.x, y)
        }
        BlizzardDirection.LEFT -> {
            val x = if (position.x - 1 < xmin) xmax - 1
            else position.x - 1
            Coordinate2D(x, position.y)
        }
        BlizzardDirection.RIGHT -> {
            val x = if (position.x + 1 == xmax) xmin
            else position.x + 1
            Coordinate2D(x, position.y)
        }
    }
}
