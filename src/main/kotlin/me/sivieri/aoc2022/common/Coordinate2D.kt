package me.sivieri.aoc2022.common

import kotlin.math.pow

data class Coordinate2D(
    val x: Int,
    val y: Int
): Comparable<Coordinate2D> {

    fun distance(other: Coordinate2D): Int =
        kotlin.math.sqrt((other.x - x).toDouble().pow(2) + (other.y - y).toDouble().pow(2) + (other.y - y).toDouble().pow(2)).toInt()

    override fun compareTo(other: Coordinate2D): Int = when {
        ORIGIN.distance(this) < ORIGIN.distance(other) -> -1
        ORIGIN.distance(this) > ORIGIN.distance(other) -> 1
        else -> 0
    }

    override fun toString(): String {
        return "($x, $y)"
    }


    companion object {
        val ORIGIN = Coordinate2D(0, 0)

        fun parseString(s: String, separator: String = ","): Coordinate2D {
            val (x, y) = s.split(separator, limit = 2)
            return Coordinate2D(x.toInt(), y.toInt())
        }

    }

}
