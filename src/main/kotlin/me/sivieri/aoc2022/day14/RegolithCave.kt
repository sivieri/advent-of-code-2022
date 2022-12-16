package me.sivieri.aoc2022.day14

import me.sivieri.aoc2022.common.Coordinate2D

class RegolithCave(input: List<String>) {

    private val paths = input.map { s ->
        s.split(" -> ").map { p ->
            val (x, y) = p.split(",", limit = 2)
            Coordinate2D(x.toInt(), y.toInt())
        }
    }
    private val minX = paths.flatten().minOf { it.x }
    private val minY = paths.flatten().minOf { it.y }
    private val maxX = paths.flatten().maxOf { it.x } + 1
    private val maxY = paths.flatten().maxOf { it.y } + 1
    private val matrix = List(maxY - minY) { MutableList(maxX - minX) { AIR } }

    init {
        paths.forEach { path ->
            path.zipWithNext().forEach { (start, end) ->
                val (actualStart, actualEnd) = listOf(start, end).sorted()
                if (actualStart.x != actualEnd.x) {
                    (actualStart.x..actualEnd.x).forEach { x ->
                        matrix[actualStart.y - minY][x - minX] = ROCK
                    }
                }
                else {
                    (actualStart.y..actualEnd.y).forEach { y ->
                        matrix[y - minY][actualStart.x - minX] = ROCK
                    }
                }
            }
        }
    }

    fun stringRepresentation(): String = matrix.joinToString("\n") { line ->
        line.joinToString("")
    }

    companion object {
        private const val AIR = '.'
        private const val ROCK = '#'
        private const val SAND = 'o'
    }

}