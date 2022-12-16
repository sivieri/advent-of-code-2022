package me.sivieri.aoc2022.day14

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(14)
        val cave = RegolithCave(data)
        println(cave.stringRepresentation())
        val result = cave.fillSandUnits()
        println()
        println(cave.stringRepresentation())
        println(result)
    }

}