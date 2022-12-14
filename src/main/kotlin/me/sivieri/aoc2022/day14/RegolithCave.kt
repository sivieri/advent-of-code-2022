package me.sivieri.aoc2022.day14

import me.sivieri.aoc2022.common.Coordinate2D

class RegolithCave(input: List<String>) {

    private val paths = input.map { s ->
        s.split(" -> ").map { p ->
            val (x, y) = p.split(",", limit = 2)
            Coordinate2D(x.toInt(), y.toInt())
        }
    }
    private val maxX = paths.flatten().maxOf { it.x } + 1
    private val maxY = paths.flatten().maxOf { it.y } + 1
    private val matrix = List(maxY) { MutableList(maxX) { AIR } }

    init {
        paths.forEach { path ->
            path.zipWithNext().forEach { (start, end) ->
                if (start.x != end.x) {
                    (start.x..end.x).forEach { x ->
                        matrix[start.y][x] = ROCK
                    }
                }
                else {
                    (start.y..end.y).forEach { y ->
                        matrix[y][start.x] = ROCK
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