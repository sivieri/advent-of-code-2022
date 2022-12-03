package me.sivieri.aoc2022.day3

import me.sivieri.aoc2022.halve
import me.sivieri.aoc2022.tail

@Suppress("ConvertArgumentToSet")
class RucksackOrdering(
    private val data: List<String>
) {

    fun findGlobalCommonValue(): Int = data.sumOf {
        val (part1, part2) = it.halve()
        Rucksack(part1, part2).findCommonElementsValue()
    }

    fun findBadgesValue(): Int = data
        .chunked(3)
        .sumOf {
            val common = it.tail().fold(it.first().toList()) { acc, value ->
                acc.intersect(value.toList()).toList()
            }
            common.sumOf { Rucksack.priorities[it]!! }
        }

}