package me.sivieri.aoc2022.day3

import me.sivieri.aoc2022.halve

class RucksackOrdering(
    private val data: List<String>
) {

    fun findGlobalCommonValue(): Int = data.sumOf {
        val (part1, part2) = it.halve()
        Rucksack(part1, part2).findCommonElementsValue()
    }

}