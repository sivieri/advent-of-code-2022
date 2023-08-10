package me.sivieri.aoc2022.day19

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(19).subList(0, 3)
        val blueprintSelector = BlueprintSelectorViaRobot(data, ResourceOrientedConstructionPlan())
        val result = blueprintSelector.calculateProduct(32)
        println(result)
    }

}