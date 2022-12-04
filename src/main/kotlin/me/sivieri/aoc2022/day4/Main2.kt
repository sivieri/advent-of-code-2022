package me.sivieri.aoc2022.day4

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(4)
        val campCleaning = CampCleaning(data)
        val result = campCleaning.countGlobalOverlaps()
        println(result)
    }

}