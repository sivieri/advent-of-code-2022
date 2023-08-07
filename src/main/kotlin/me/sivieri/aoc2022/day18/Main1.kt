package me.sivieri.aoc2022.day18

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(18)
        val droplet = Droplet(data)
        val result = droplet.calculateSurface()
        println(result)
    }

}