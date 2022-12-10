package me.sivieri.aoc2022.day10

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(10)
        val interpreter = VideoInterpreter(data)
        val result = interpreter.findTotalSignalStrength(20, 40, 220)
        println(result)
    }

}