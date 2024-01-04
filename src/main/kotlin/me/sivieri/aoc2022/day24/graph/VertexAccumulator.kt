package me.sivieri.aoc2022.day24.graph

import me.sivieri.aoc2022.common.Coordinate3D
import me.sivieri.aoc2022.day24.Valley

data class VertexAccumulator(
    val vertex: Coordinate3D,
    val weight: Int,
    val vertices: List<VisitedCell>,
    val minutes: Int,
    val valley: Valley
): Comparable<VertexAccumulator> {
    override fun compareTo(other: VertexAccumulator): Int = this.weight - other.weight
}
