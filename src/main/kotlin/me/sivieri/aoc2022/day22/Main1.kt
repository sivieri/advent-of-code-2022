package me.sivieri.aoc2022.day22

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(22)
        val map = MonkeyMap(data)
        val result = map.play()
        println(result)
    }

}