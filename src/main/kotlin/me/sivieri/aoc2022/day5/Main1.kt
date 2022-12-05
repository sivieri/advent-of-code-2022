package me.sivieri.aoc2022.day5

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputBlocks(5) { it }
        val crane = Crane(data[0], data[1])
        crane.moveAll()
        val result = crane.getTopStatus()
        println(result)
    }

}