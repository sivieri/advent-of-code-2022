package me.sivieri.aoc2022.day16

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(16)
        val net = ValveNetwork(data)
        val result = net.getMaxFlowWithHelp()
        println(result)
    }

}