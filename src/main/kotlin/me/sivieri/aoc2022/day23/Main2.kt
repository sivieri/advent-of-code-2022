package me.sivieri.aoc2022.day23

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(23)
        val grove = GroveExploration(data)
        val result = grove.countRounds()
        println(result)
    }

}