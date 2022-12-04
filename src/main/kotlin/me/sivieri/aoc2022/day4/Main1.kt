package me.sivieri.aoc2022.day4

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(4)
        val campCleaning = CampCleaning(data)
        val result = campCleaning.countCompleteOverlapInPairs()
        println(result)
    }

}