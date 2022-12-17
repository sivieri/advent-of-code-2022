package me.sivieri.aoc2022.day15

import me.sivieri.aoc2022.Utils

object Main2 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(15)
        val tunnelsMonitoring = TunnelsMonitoring(data)
        val result = tunnelsMonitoring.findDistressBeacon(0, 4000000)
        println(result)
    }

}