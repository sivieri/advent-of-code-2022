package me.sivieri.aoc2022.day11

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputBlocks(11) { it }
        val game = KeepAwayGame(data)
        game.play(10000)
        val result = game.calculateMonkeyBusiness()
        println(result)
    }

}