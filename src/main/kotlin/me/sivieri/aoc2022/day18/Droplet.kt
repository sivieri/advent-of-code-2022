package me.sivieri.aoc2022.day18

import me.sivieri.aoc2022.common.Coordinate3D

class Droplet(
    data: List<String>
) {

    private val coordinates = data.map {
        val (x, y, z) = it.split(",", limit = 3)
        Coordinate3D(x.toInt(), y.toInt(), z.toInt())
    }.toSet()

    fun calculateSurface(): Int = coordinates.fold(0) { acc, c ->
        val freeNeighbors = listOf(
            c.copy(x = c.x + 1),
            c.copy(x = c.x - 1),
            c.copy(y = c.y + 1),
            c.copy(y = c.y - 1),
            c.copy(z = c.z + 1),
            c.copy(z = c.z - 1)
        ) - coordinates
        acc + freeNeighbors.size
    }

}