package me.sivieri.aoc2022.day23

import me.sivieri.aoc2022.common.Coordinate2D

class Grove(data: String) {

    private val matrix: MutableMap<Int, MutableMap<Int, Char>> = data
        .split("\n")
        .filter { it.isNotBlank() }
        .mapIndexed { y, s ->
            y to s
                .toList()
                .mapIndexed { x, c -> x to c }
                .toMap()
                .toMutableMap()
        }
        .toMap()
        .toMutableMap()
    private var xmin = 0
    private var ymin = 0
    private var xmax: Int = matrix[0]!!.size
    private var ymax: Int = matrix.size

    fun getSymbol(position: Coordinate2D): Char =
        if (position.x !in (xmin until xmax) || position.y !in (ymin until ymax)) EMPTY
        else matrix[position.y]?.get(position.x) ?: EMPTY

    fun setSymbol(position: Coordinate2D, symbol: Char) {
        when {
            position.x < xmin -> {
                matrix.forEach {
                    it.value[position.x] = EMPTY
                }
                xmin--
            }
            position.x == xmax -> {
                matrix.forEach {
                    it.value[position.x] = EMPTY
                }
                xmax++
            }
            position.y < ymin -> {
                matrix[position.y] = (0 until xmax).associateWith { EMPTY }.toMutableMap()
                ymin--
            }
            position.y == ymax -> {
                matrix[position.y] = (0 until xmax).associateWith { EMPTY }.toMutableMap()
                ymax++
            }
            else -> { }
        }
        matrix[position.y]!![position.x] = symbol
    }

    fun findAllPositions(symbol: Char): Set<Coordinate2D> = (ymin until ymax)
        .flatMap { y ->
            val d = matrix.getOrDefault(
                y,
                (0 until xmax).associateWith { EMPTY }.toMutableMap()
            )
            (xmin until xmax).mapNotNull { x ->
                if (d.getOrDefault(x, EMPTY) == symbol) Coordinate2D(x, y)
                else null
            }
        }
        .toSet()

    fun stringRepresentation(): String =
        (ymin until ymax).joinToString("\n") { y ->
            val d = matrix.getOrDefault(
                y,
                (0 until xmax).associateWith { EMPTY }.toMutableMap()
            )
            (xmin until xmax).joinToString("") { x ->
                d.getOrDefault(x, EMPTY).toString()
            }
        }

    companion object {
        private const val EMPTY = '.'
        private const val ELF = '#'
    }

}