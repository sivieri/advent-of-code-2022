package me.sivieri.aoc2022.day10

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(10)
        val interpreter = VideoInterpreter(data)
        val result = interpreter.render()
        println(result)
    }

}