package me.sivieri.aoc2022.day19

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(19)
        val blueprintSelector = BlueprintSelectorViaTime(data, ResourceOrientedConstructionPlan())
        val result = blueprintSelector.calculateTotalValue(24)
        println(result)
    }

}