package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputBlocks(13) { it }
        val lc = ListsComparison(data)
        val result = lc.sumRightPairs()
        println(result)
    }

}