package me.sivieri.aoc2022.day24

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.common.Coordinate3D

data class Valley(
    val blizzards: List<Blizzard>,
    val xmax: Int,
    val ymax: Int
) {
    fun move(): Valley = Valley(blizzards.map { it.move() }, xmax, ymax)

    fun freeLocations(locations: List<Coordinate3D>): List<Coordinate3D> = locations
        .filter { o -> blizzards.all { b -> b.nextPosition != o.to2D() } }

    fun stringRepresentation(): String {
        val coords = blizzards.associate { it.position to it.direction.symbol }
        return (0 until ymax).joinToString("\n") { y ->
            (0 until xmax).joinToString("") { x ->
                val default = if (x == 0 || x == xmax -1 || y == 0 || y == ymax - 1) WALL
                else EMPTY
                coords.getOrDefault(Coordinate2D(x, y), default).toString()
            }
        }
    }

    companion object {
        private const val WALL = '#'
        private const val EMPTY = '.'
    }
}
