package me.sivieri.aoc2022.day12

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(12)
        val hillClimbing = MultipleHillClimbing(data)
        val result = hillClimbing.findShortestPathStepNumber()
        println(result)
    }

}