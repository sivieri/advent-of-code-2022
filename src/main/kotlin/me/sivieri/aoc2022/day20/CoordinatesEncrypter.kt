package me.sivieri.aoc2022.day20

import kotlin.math.abs

class CoordinatesEncrypter {

    fun decrypt(data: List<Int>): Int {
        val size = data.size
        val mixed = data.toTypedArray()
        data.forEach { v ->
            (0 until abs(v)).forEach { _ ->
                val index = mixed.indexOf(v)
                val newIndex = if (v < 0) (index - 1).mod(size) else (index + 1).mod(size)
                val swap = mixed[newIndex]
                mixed[newIndex] = mixed[index]
                mixed[index] = swap
            }
        }
        return mixed[getValue(FIRST_INDEX + mixed.indexOf(0), size)] +
            mixed[getValue(SECOND_INDEX+ mixed.indexOf(0), size)] +
            mixed[getValue(THIRD_INDEX+ mixed.indexOf(0), size)]
    }

    private fun getValue(index: Int, size: Int): Int = index % size

    companion object {
        private const val FIRST_INDEX = 1000
        private const val SECOND_INDEX = 2000
        private const val THIRD_INDEX = 3000
    }

}