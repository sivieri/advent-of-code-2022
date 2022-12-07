package me.sivieri.aoc2022.day7

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(7)
        val interpreter = Interpreter(data)
        val result = interpreter.findSizeOfSmallestToDelete(70000000, 30000000)
        println(result)
    }

}