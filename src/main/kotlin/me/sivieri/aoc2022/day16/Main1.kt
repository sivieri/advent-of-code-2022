package me.sivieri.aoc2022.day16

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(16)
        val net = ValveNetwork(data)
        val result = net.getMaxFlow()
        println(result)
    }

}