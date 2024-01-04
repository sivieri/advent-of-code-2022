package me.sivieri.aoc2022.day25

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(25)
        val bob = Bob(data)
        val result = bob.calculateTotal()
        println(result)
        println(result.toSnafu())
    }

}