package me.sivieri.aoc2022.day19

sealed class Robot {
    abstract fun isFeasible(status: ExtractionStatus): Boolean
    abstract fun updateStatusResources(status: ExtractionStatus): ExtractionStatus
    abstract override fun toString(): String
}

data class OreRobot(val ore: Int): Robot() {
    override fun isFeasible(status: ExtractionStatus): Boolean = status.ore >= ore
    override fun updateStatusResources(status: ExtractionStatus): ExtractionStatus = status.copy(ore = status.ore - ore)
}

data class ClayRobot(val ore: Int): Robot() {
    override fun isFeasible(status: ExtractionStatus): Boolean = status.ore >= ore
    override fun updateStatusResources(status: ExtractionStatus): ExtractionStatus = status.copy(ore = status.ore - ore)
}

data class ObsidianRobot(val ore: Int, val clay: Int): Robot() {
    override fun isFeasible(status: ExtractionStatus): Boolean = status.ore >= ore && status.clay >= clay
    override fun updateStatusResources(status: ExtractionStatus): ExtractionStatus = status.copy(ore = status.ore - ore, clay = status.clay - clay)
}

data class GeodeRobot(val ore: Int, val obsidian: Int): Robot() {
    override fun isFeasible(status: ExtractionStatus): Boolean = status.ore >= ore && status.obsidian >= obsidian
    override fun updateStatusResources(status: ExtractionStatus): ExtractionStatus = status.copy(ore = status.ore - ore, obsidian = status.obsidian - obsidian)
}
