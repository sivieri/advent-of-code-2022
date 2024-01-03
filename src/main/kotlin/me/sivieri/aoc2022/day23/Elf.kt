package me.sivieri.aoc2022.day23

import me.sivieri.aoc2022.common.Coordinate2D

data class Elf(
    val id: Int,
    val position: Coordinate2D,
    val possibleDirections: List<GroveDirection>,
    val nextPosition: Coordinate2D? = null
)
