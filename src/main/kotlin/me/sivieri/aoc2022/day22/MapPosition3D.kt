package me.sivieri.aoc2022.day22

import me.sivieri.aoc2022.common.Coordinate2D

data class MapPosition3D(
    val board: Int,
    val position: Coordinate2D,
    val direction: MapDirection
)
