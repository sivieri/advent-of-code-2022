package me.sivieri.aoc2022.day21

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(21)
        val riddle = MonkeyRiddle(data)
        val result = riddle.findRootNumber()
        println(result)
    }

}