package me.sivieri.aoc2022.day19

import kotlin.math.min

class BlueprintSelector(
    data: List<String>
) {

    private val blueprints = data.map {
        val blueprint = Blueprint.parse(it)
        blueprint.id to blueprint
    }.toMap()

    fun calculateTotalValue(time: Int) = calculateBlueprintsValue(time).entries.sumOf { it.key * it.value }

    fun calculateBlueprintsValue(time: Int): Map<Int, Int> = blueprints.values.associate {
        it.id to calculateBlueprintValue(time, it.id)
    }

    fun calculateBlueprintValue(time: Int, id: Int): Int {
        val blueprint = blueprints.getValue(id)
        var queue = listOf(ExtractionStatus())
        (1..time).forEach {  minute ->
            println("Minute $minute")
            var currentConstruction: Robot? = null
            queue = queue.flatMap { status ->
                // decide what we can construct
                val geodePossibleRobot = min(
                    status.obsidian / blueprint.geodeRobot.obsidian,
                    status.ore / blueprint.geodeRobot.ore,
                )
                val constructionCombinations = (0..geodePossibleRobot).flatMap { geodeRobots ->
                    val geodeLess = status.copy(
                        obsidian = status.obsidian - blueprint.geodeRobot.obsidian * geodeRobots,
                        ore = status.ore - blueprint.geodeRobot.ore * geodeRobots,
                    )
                    val obsidianPossibleRobot = min(
                        geodeLess.clay / blueprint.obsidianRobot.clay,
                        geodeLess.ore / blueprint.obsidianRobot.ore,
                    )
                    (0..obsidianPossibleRobot).flatMap { obsidianRobots ->
                        val obsidianLess = geodeLess.copy(
                            clay = geodeLess.clay - blueprint.obsidianRobot.clay * obsidianRobots,
                            ore = geodeLess.ore - blueprint.obsidianRobot.ore * obsidianRobots,
                        )
                        val clayPossibleRobot = obsidianLess.ore / blueprint.clayRobot.ore
                        (0..clayPossibleRobot).flatMap { clayRobots ->
                            val clayLess = obsidianLess.copy(ore = obsidianLess.ore - blueprint.clayRobot.ore * clayRobots)
                            val orePossibleRobot = clayLess.ore / blueprint.oreRobot.ore
                            (0..orePossibleRobot).map { oreRobots ->
                                clayLess.copy(ore = clayLess.ore - blueprint.oreRobot.ore * oreRobots)
                            }
                        }
                    }
                }

                // collect new resources
                constructionCombinations.map { s ->
                    val resourcesEnriched = s.copy(
                        ore = s.ore + s.oreRobots,
                        clay = s.clay + s.clayRobots,
                        obsidian = s.obsidian + s.obsidianRobots,
                        geode = s.geode + s.geodeRobots,
                    )
                    // end construction (if any)
                    when (currentConstruction) {
                        is ClayRobot -> resourcesEnriched.copy(clayRobots = resourcesEnriched.clayRobots + 1)
                        is GeodeRobot -> resourcesEnriched.copy(geodeRobots = resourcesEnriched.geodeRobots + 1)
                        is ObsidianRobot -> resourcesEnriched.copy(obsidianRobots = resourcesEnriched.obsidianRobots + 1)
                        is OreRobot -> resourcesEnriched.copy(oreRobots = resourcesEnriched.oreRobots + 1)
                        null -> resourcesEnriched
                    }
                }
            }
        }
        return queue.maxOf { it.geode }
    }
}