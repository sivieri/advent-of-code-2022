package me.sivieri.aoc2022.day9

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInputToList(9)
            .filter { it.isNotBlank() }
            .map { RopeMovement.parseLine(it) }
        val bridge = LongRopeBridge(9)
        bridge.move(data)
        val result = bridge.countTailPositions()
        println(result)
    }

}