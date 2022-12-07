package me.sivieri.aoc2022.day7

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(7)
        val interpreter = Interpreter(data)
        println(interpreter)
        println(interpreter.root.countAllFiles())
        println(interpreter.root.countAllDirectories())
        val result = interpreter.findTotalSizeOfAtMost(100000)
        println(result)
    }

}