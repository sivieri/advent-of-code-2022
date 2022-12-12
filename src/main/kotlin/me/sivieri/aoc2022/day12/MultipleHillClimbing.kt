package me.sivieri.aoc2022.day12

import me.sivieri.aoc2022.common.Coordinate2D

class MultipleHillClimbing(input: List<String>) {

    private val matrix = input.map { it.toList().toMutableList() }
    private val maxX = matrix.first().size
    private val maxY = matrix.size
    private var currentStart: Coordinate2D? = null

    init {
        (0 until maxY).forEach { y ->
            (0 until maxX).forEach { x ->
                if (matrix[y][x] == 'S') currentStart = Coordinate2D(x, y)
            }
        }
    }

    fun findShortestPathStepNumber(): Int {
        val allBeginnings = (0 until maxY).flatMap { y ->
            (0 until maxX).mapNotNull { x ->
                if (matrix[y][x] == 'S' || matrix[y][x] == 'a') Coordinate2D(x, y)
                else null
            }
        }
        return allBeginnings.minOf { c ->
            matrix[currentStart!!.y][currentStart!!.x] = 'a'
            matrix[c.y][c.x] = 'S'
            currentStart = c.copy()
            val input = matrix.map { it.joinToString("") }
            val hillClimbing = HillClimbing(input)
            try {
                hillClimbing.findShortestPathStepNumber()
            } catch (e: Exception) { // Something wrong with edges... ignore
                Int.MAX_VALUE
            }
        }
    }

}