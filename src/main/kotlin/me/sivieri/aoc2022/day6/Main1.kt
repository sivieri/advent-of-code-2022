package me.sivieri.aoc2022.day6

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInput(6)
        val communicationSystem = CommunicationSystem(data)
        val result = communicationSystem.countPremarkerData()
        println(result)
    }

}