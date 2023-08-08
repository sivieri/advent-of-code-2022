package me.sivieri.aoc2022.day19

sealed class Robot {
    abstract override fun toString(): String
}

data class OreRobot(val ore: Int): Robot()

data class ClayRobot(val ore: Int): Robot()

data class ObsidianRobot(val ore: Int, val clay: Int): Robot()

data class GeodeRobot(val ore: Int, val obsidian: Int): Robot()
