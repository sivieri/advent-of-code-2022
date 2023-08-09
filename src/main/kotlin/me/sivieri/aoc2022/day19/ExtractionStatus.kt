package me.sivieri.aoc2022.day19

data class ExtractionStatus(
    val oreRobots: Int = 1,
    val clayRobots: Int = 0,
    val obsidianRobots: Int = 0,
    val geodeRobots: Int = 0,
    val ore: Int = 0,
    val clay: Int = 0,
    val obsidian: Int = 0,
    val geode: Int = 0
) {
    fun compact(): Array<Int> = arrayOf(oreRobots, clayRobots, obsidianRobots, geodeRobots, ore, clay, obsidian, geode)

    companion object {
        const val GEODE_POSITION = 7

        fun fromCompact(array: Array<Int>): ExtractionStatus = ExtractionStatus(
            array[0],
            array[1],
            array[2],
            array[3],
            array[4],
            array[5],
            array[6],
            array[7]
        )
    }
}
