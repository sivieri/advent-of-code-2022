package me.sivieri.aoc2022.day19

data class Blueprint(
    val id: Int,
    val oreRobot: OreRobot,
    val clayRobot: ClayRobot,
    val obsidianRobot: ObsidianRobot,
    val geodeRobot: GeodeRobot
) {

    companion object {
        private val regex = "Blueprint ([0-9]+): Each ore robot costs ([0-9]+) ore. Each clay robot costs ([0-9]+) ore. Each obsidian robot costs ([0-9]+) ore and ([0-9]+) clay. Each geode robot costs ([0-9]+) ore and ([0-9]+) obsidian.".toRegex()

        fun parse(data: String): Blueprint =
            regex
                .matchEntire(data)
                ?.destructured
                ?.let { (id, ore1, ore2, ore3, clay, ore4, obsidian) ->
                    Blueprint(
                        id.toInt(),
                        OreRobot(ore1.toInt()),
                        ClayRobot(ore2.toInt()),
                        ObsidianRobot(ore3.toInt(), clay.toInt()),
                        GeodeRobot(ore4.toInt(), obsidian.toInt())
                    )
                }
                ?: throw IllegalArgumentException("Unable to parse $data")
    }

}
