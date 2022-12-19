package me.sivieri.aoc2022.day16

data class QueueStatus(
    val valve: Valve,
    val remaining: Int,
    val relief: Int,
    val openedValves: Set<Valve>
)
