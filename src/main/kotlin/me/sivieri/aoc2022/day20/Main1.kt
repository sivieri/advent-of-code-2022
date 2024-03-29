package me.sivieri.aoc2022.day20

import me.sivieri.aoc2022.Utils

object Main1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val data = Utils
            .readInputToList(20)
            .filter { it.isNotBlank() }
            .map { it.toInt() }
        val coordinatesEncrypter = CoordinatesEncrypter()
        val result = coordinatesEncrypter.decrypt(data)
        println(result)
    }

}