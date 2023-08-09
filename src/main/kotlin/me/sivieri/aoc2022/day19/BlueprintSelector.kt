package me.sivieri.aoc2022.day19

class BlueprintSelector(
    data: List<String>,
    private val constructionPlan: ConstructionPlan
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
        var queue = listOf(ExtractionStatus().compact())
        (1..time).forEach {  minute ->
            println("Minute $minute: queue size ${queue.size}")
            queue = queue.flatMap { statusArray ->
                val status = ExtractionStatus.fromCompact(statusArray)
                // decide what we can construct
                val constructionCombinations = constructionPlan.executePlan(blueprint, status)

                val final = constructionCombinations.map { (s, c) ->
                    // collect new resources
                    val resourcesEnriched = s.copy(
                        ore = s.ore + s.oreRobots,
                        clay = s.clay + s.clayRobots,
                        obsidian = s.obsidian + s.obsidianRobots,
                        geode = s.geode + s.geodeRobots,
                    )

                    // end construction (if any)
                    when (c) {
                        is ClayRobot -> resourcesEnriched.copy(clayRobots = resourcesEnriched.clayRobots + 1)
                        is GeodeRobot -> resourcesEnriched.copy(geodeRobots = resourcesEnriched.geodeRobots + 1)
                        is ObsidianRobot -> resourcesEnriched.copy(obsidianRobots = resourcesEnriched.obsidianRobots + 1)
                        is OreRobot -> resourcesEnriched.copy(oreRobots = resourcesEnriched.oreRobots + 1)
                        null -> resourcesEnriched
                    }.compact()
                }
                final
            }
        }
        return queue.maxOf { it[ExtractionStatus.GEODE_POSITION] }
    }

}