package me.sivieri.aoc2022.day19

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
            val next = emptyList<ExtractionStatus>()
            queue.forEach { status ->
                // end construction (if any)
                val constructionUpdate = when (status.currentConstruction) {
                    is ClayRobot -> status.copy(clayRobots = status.clayRobots + 1)
                    is GeodeRobot -> status.copy(geodeRobots = status.geodeRobots + 1)
                    is ObsidianRobot -> status.copy(obsidianRobots = status.obsidianRobots + 1)
                    is OreRobot -> status.copy(oreRobots = status.oreRobots + 1)
                    null -> status
                }

                // decide what we can construct


                // collect new resources

            }
            queue = next
        }
        return queue.maxOf { it.geode }
    }
}