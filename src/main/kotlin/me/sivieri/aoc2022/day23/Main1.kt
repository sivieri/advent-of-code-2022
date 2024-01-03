package me.sivieri.aoc2022.day23

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(23)
        val grove = GroveExploration(data)
        val result = grove.countEmptyGround(10)
        println(result)
    }

}