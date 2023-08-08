package me.sivieri.aoc2022.day19

data class ExtractionStatus(
    val currentConstruction: Robot? = null,
    val oreRobots: Int = 1,
    val clayRobots: Int = 0,
    val obsidianRobots: Int = 0,
    val geodeRobots: Int = 0,
    val ore: Int = 0,
    val clay: Int = 0,
    val obisidan: Int = 0,
    val geode: Int = 0
)
