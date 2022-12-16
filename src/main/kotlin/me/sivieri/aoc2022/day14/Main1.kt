package me.sivieri.aoc2022.day14

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(14)
        val cave = RegolithCave(data)
        val result = cave.fillSandUnits()
        println(result)
    }

}