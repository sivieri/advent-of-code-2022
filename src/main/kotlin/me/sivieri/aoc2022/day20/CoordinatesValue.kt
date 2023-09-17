package me.sivieri.aoc2022.day20

data class CoordinatesValue(
    val value: Int,
    val position: Int
)

internal fun Array<CoordinatesValue>.indexOfByPosition(position: Int): Int =
    this.indexOf(this.find { it.position == position })

internal fun Array<CoordinatesValue>.indexOfByValue(value: Int): Int =
    this.indexOf(this.find { it.value == value })
