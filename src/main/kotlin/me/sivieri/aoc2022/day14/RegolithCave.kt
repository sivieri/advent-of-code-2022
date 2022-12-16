package me.sivieri.aoc2022.day14

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.zipWithIndex

class RegolithCave(input: List<String>) {

    private val paths = input.map { s ->
        s.split(" -> ").map { p ->
            val (x, y) = p.split(",", limit = 2)
            Coordinate2D(x.toInt(), y.toInt())
        }
    }
    private val minX = paths.flatten().minOf { it.x }
    private val minY = sandInsertion.y
    private val maxX = paths.flatten().maxOf { it.x } + 1
    private val maxY = paths.flatten().maxOf { it.y } + 1
    private val matrix = List(maxY - minY) { MutableList(maxX - minX) { AIR } }

    init {
        paths.forEach { path ->
            path.zipWithNext().forEach { (start, end) ->
                if (start.x != end.x) {
                    val actualStart = if (start.x < end.x) start.x else end.x
                    val actualEnd = if (start.x < end.x) end.x else start.x
                    (actualStart..actualEnd).forEach { x ->
                        matrix[start.y - minY][x - minX] = ROCK
                    }
                }
                else {
                    val actualStart = if (start.y < end.y) start.y else end.y
                    val actualEnd = if (start.y < end.y) end.y else start.y
                    (actualStart..actualEnd).forEach { y ->
                        matrix[y - minY][start.x - minX] = ROCK
                    }
                }
            }
        }
        matrix[sandInsertion.y - minY][sandInsertion.x - minX] = INS
    }

    fun addSandUnit(): Boolean {
        var result = true
        var prev = Coordinate2D(sandInsertion.x, sandInsertion.y - 1)
        var cur = sandInsertion.copy()
        while (prev != cur) {
            prev = cur
            cur = when {
                !isValid(Coordinate2D(cur.x, cur.y + 1))
                    || !isValid(Coordinate2D(cur.x - 1, cur.y + 1))
                    || !isValid(Coordinate2D(cur.x + 1, cur.y + 1)) -> {
                    result = false
                    cur
                }
                isValid(Coordinate2D(cur.x, cur.y + 1)) && matrix[cur.y - minY + 1][cur.x - minX] == AIR -> Coordinate2D(cur.x, cur.y + 1)
                isValid(Coordinate2D(cur.x - 1, cur.y + 1)) && matrix[cur.y - minY + 1][cur.x - minX - 1] == AIR -> Coordinate2D(cur.x - 1, cur.y + 1)
                isValid(Coordinate2D(cur.x + 1, cur.y + 1)) && matrix[cur.y - minY + 1][cur.x - minX + 1] == AIR -> Coordinate2D(cur.x + 1, cur.y + 1)
                else -> {
                    matrix[cur.y - minY][cur.x - minX] = SAND
                    cur
                }
            }
        }
        return result
    }

    fun fillSandUnits(): Int {
        var i = 0
        while (addSandUnit()) i++
        return i
    }

    private fun isValid(c: Coordinate2D): Boolean = !(c.x < minX || c.x >= maxX || c.y < minY || c.y >= maxY)

    fun stringRepresentation(): String = matrix
        .zipWithIndex { it }
        .joinToString("\n") { (index, line) ->
            index.toString().padStart(3, '0') + " " + line.joinToString("")
        }

    companion object {
        private const val AIR = '.'
        private const val ROCK = '#'
        private const val SAND = 'o'
        private const val INS = '+'
        private val sandInsertion = Coordinate2D(500, 0)
    }

}