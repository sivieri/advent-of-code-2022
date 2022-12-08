package me.sivieri.aoc2022.day8

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(8)
        val forest = Forest(data)
        val result = forest.countVisibleTrees()
        println(result)
    }

}