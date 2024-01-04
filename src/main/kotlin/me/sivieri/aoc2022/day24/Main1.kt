package me.sivieri.aoc2022.day24

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(24)
        val simulator = ValleySimulator(data)
        val path = simulator.findThePath(true)
        println(path)
    }

}