package me.sivieri.aoc2022.day2

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInputToList(2)
            .map {
                val (a, b) = it.split(" ", limit = 2)
                a.first() to b.first()
            }
        val mapping = mapOf(
            'X' to RPSMove.Rock,
            'Y' to RPSMove.Paper,
            'Z' to RPSMove.Scissors
        )
        val strategy = Strategy()
        val result = strategy.resolve(data, mapping)
        println(result)
    }

}