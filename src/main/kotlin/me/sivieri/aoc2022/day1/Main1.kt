package me.sivieri.aoc2022.day1

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val result = Utils
            .readInputBlocks(1) {
                it
                    .split("\n")
                    .filterNot { it.isBlank() }
                    .sumOf { it.toInt() }
            }
            .max()
        println(result)
    }

}