package me.sivieri.aoc2022.day14

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(14)
        val cave = RegolithCave(data, withFloor = true)
        println(cave.stringRepresentation())
        val result = cave.reallyFillSandUnits()
        println()
        println(cave.stringRepresentation())
        println(result)
    }

}