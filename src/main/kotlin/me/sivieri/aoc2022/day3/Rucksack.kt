package me.sivieri.aoc2022.day3

@Suppress("ConvertArgumentToSet")
data class Rucksack(
    val firstCompartment: String,
    val secondCompartment: String
) {

    fun findCommonElementsValue(): Int =
        firstCompartment
            .toList()
            .intersect(secondCompartment.toList())
            .sumOf { priorities[it]!! }

    companion object {
        val priorities = ('a'..'z').zip(1..26)
            .union(('A'..'Z').zip(27..52))
            .toMap()
    }

}
