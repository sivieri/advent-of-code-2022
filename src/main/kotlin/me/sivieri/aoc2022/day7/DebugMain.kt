package me.sivieri.aoc2022.day7

import me.sivieri.aoc2022.Utils

object DebugMain {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils.readInputToList(7)
        data
            .mapIndexed { index, s ->
                if (s.startsWith("dir")) {
                    val prev = data.subList(0, index).findLast { it.startsWith("$ cd") && !it.contains("..") }!!
                    val name = s.split(" ")[1]
                    val parent = prev.split(" ")[2]
                    "$name (${parent})"
                }
                else null
            }
            .filterNotNull()
            .sorted()
            .forEach { println(it) }
    }

}