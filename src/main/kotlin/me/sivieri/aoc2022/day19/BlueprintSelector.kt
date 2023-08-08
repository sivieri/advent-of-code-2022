package me.sivieri.aoc2022.day19

class BlueprintSelector(
    data: List<String>
) {

    private val blueprints = data.map { Blueprint.parse(it) }

    fun calculateTotalValue(time: Int) = calculateBlueprintsValue(time).entries.sumOf { it.key * it.value }

    fun calculateBlueprintsValue(time: Int): Map<Int, Int> = blueprints.associate {
        it.id to calculateBlueprintValue(time, it.id)
    }

    fun calculateBlueprintValue(time: Int, id: Int): Int {
        TODO()
    }
}