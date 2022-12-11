package me.sivieri.aoc2022.day11

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputBlocks(11) { it }
        val game = KeepAwayGame(data)
        game.play(20)
        val result = game.calculateMonkeyBusiness()
        println(result)
    }

}