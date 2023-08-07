package me.sivieri.aoc2022.day18

import me.sivieri.aoc2022.common.Coordinate3D
import me.sivieri.aoc2022.day18.Droplet.Companion.neighbors
import me.sivieri.aoc2022.zipWithIndex
import java.util.Stack

@Suppress("ConvertArgumentToSet")
class Droplet(
    data: List<String>
) {

    private val coordinates = data
        .map {
            val (x, y, z) = it.split(",", limit = 3)
            Coordinate3D(x.toInt(), y.toInt(), z.toInt())
        }
        .toSet()

    fun calculateSurface(): Int = coordinates.fold(0) { acc, c ->
        val freeNeighbors = c.neighbors() - coordinates
        acc + freeNeighbors.size
    }

    fun calculateExteriorSurface(): Int {
        val maxX = coordinates.maxOf { it.x } + 1
        val maxY = coordinates.maxOf { it.y } + 1
        val maxZ = coordinates.maxOf { it.z } + 1
        val minX = coordinates.minOf { it.x } - 1
        val minY = coordinates.minOf { it.y } - 1
        val minZ = coordinates.minOf { it.z } - 1
        val boundaries = listOf(
            Coordinate3D(minX, minY, minZ),
            Coordinate3D(maxX, minY, minZ),
            Coordinate3D(minX, maxY, minZ),
            Coordinate3D(minX, minY, maxZ),
            Coordinate3D(maxX, maxY, minZ),
            Coordinate3D(maxX, minY, maxZ),
            Coordinate3D(minX, maxY, maxZ),
            Coordinate3D(maxX, maxY, maxZ)
        )
        val start = Coordinate3D(minX, minY, minZ)
        val stack = Stack<Coordinate3D>()
        stack.push(start)
        val visited = mutableSetOf<Coordinate3D>()
        while (!stack.isEmpty()) {
            val c = stack.pop()
            if (!visited.contains(c)) {
                val n = c
                    .neighbors()
                    .filter { !visited.contains(it) }
                    .filter { it.within(minX, minY, minZ, maxX, maxY, maxZ) }
                val remaining = n - coordinates
                remaining.forEach { stack.push(it) }
                visited.add(c)
            }
        }
        return coordinates.fold(0) { acc, c ->
            val freeNeighbors = (c.neighbors() - coordinates).intersect(visited)
            acc + freeNeighbors.size
        }
    }

    companion object {
        private fun Coordinate3D.neighbors() = listOf(
            this.copy(x = this.x + 1),
            this.copy(x = this.x - 1),
            this.copy(y = this.y + 1),
            this.copy(y = this.y - 1),
            this.copy(z = this.z + 1),
            this.copy(z = this.z - 1)
        )

        private fun Coordinate3D.within(
            minX: Int,
            minY: Int,
            minZ: Int,
            maxX: Int,
            maxY: Int,
            maxZ: Int
        ): Boolean = this.x in minX..maxX && this.y in minY..maxY && this.z in minZ..maxZ
    }

}