package me.sivieri.aoc2022.day3

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(3)
        val rucksackOrdering = RucksackOrdering(data)
        val result = rucksackOrdering.findGlobalCommonValue()
        println(result)
    }

}