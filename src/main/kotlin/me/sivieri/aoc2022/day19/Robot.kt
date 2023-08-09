package me.sivieri.aoc2022.day19

sealed class Robot {
    fun isFeasible(status: ExtractionStatus): Boolean =
        status.ore >= ore() &&
            status.clay >= clay() &&
            status.obsidian >= obsidian()

    fun updateStatusResources(status: ExtractionStatus): ExtractionStatus = status.copy(
        ore = status.ore - ore(),
        clay = status.clay - clay(),
        obsidian = status.obsidian - obsidian()
    )

    abstract fun ore(): Int
    abstract fun clay(): Int
    abstract fun obsidian(): Int
    abstract override fun toString(): String
}

data class OreRobot(private val ore: Int): Robot() {
    override fun ore(): Int = this.ore
    override fun clay(): Int = 0
    override fun obsidian(): Int = 0
}

data class ClayRobot(private val ore: Int): Robot() {
    override fun ore(): Int = this.ore
    override fun clay(): Int = 0
    override fun obsidian(): Int = 0
}

data class ObsidianRobot(private val ore: Int, private val clay: Int): Robot() {
    override fun ore(): Int = this.ore
    override fun clay(): Int = this.clay
    override fun obsidian(): Int = 0
}

data class GeodeRobot(private val ore: Int, private val obsidian: Int): Robot() {
    override fun ore(): Int = this.ore
    override fun clay(): Int = 0
    override fun obsidian(): Int = this.obsidian
}
