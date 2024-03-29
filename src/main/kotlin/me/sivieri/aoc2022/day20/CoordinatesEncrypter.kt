package me.sivieri.aoc2022.day20

import kotlin.math.abs

class CoordinatesEncrypter {

    fun decrypt(data: List<Int>): Int {
        val size = data.size
        val mixed = data
            .mapIndexed { index, v -> CoordinatesValue(v, index) }
            .toTypedArray()
        data.forEachIndexed { position, v ->
            (0 until abs(v)).forEach { _ ->
                val index = mixed.indexOfByPosition(position)
                val newIndex = if (v < 0) (index - 1).mod(size) else (index + 1).mod(size)
                val swap = mixed[newIndex]
                mixed[newIndex] = mixed[index]
                mixed[index] = swap
            }
        }
        return mixed[getValue(FIRST_INDEX + mixed.indexOfByValue(0), size)].value +
            mixed[getValue(SECOND_INDEX+ mixed.indexOfByValue(0), size)].value +
            mixed[getValue(THIRD_INDEX+ mixed.indexOfByValue(0), size)].value
    }

    fun decryptLong(data: List<Int>): Long {
        val updatedData = data.map { it * DECRIPTION_KEY }
        val size = updatedData.size
        val mixed = updatedData
            .mapIndexed { index, v -> CoordinatesValueLong(v, index) }
            .toTypedArray()
        (1..10).forEach { iteration ->
            println("Iteration $iteration")
            updatedData.forEachIndexed { position, v ->
                val iterations = abs(v)// % size
                (0 until iterations).forEach { _ ->
                    val index = mixed.indexOfByPosition(position)
                    val newIndex = if (v < 0) (index - 1).mod(size) else (index + 1).mod(size)
                    val swap = mixed[newIndex]
                    mixed[newIndex] = mixed[index]
                    mixed[index] = swap
                }
            }
        }
        return mixed[getValue(FIRST_INDEX + mixed.indexOfByValue(0), size)].value +
        mixed[getValue(SECOND_INDEX+ mixed.indexOfByValue(0), size)].value +
        mixed[getValue(THIRD_INDEX+ mixed.indexOfByValue(0), size)].value
    }

    private fun getValue(index: Int, size: Int): Int = index % size

    companion object {
        private const val FIRST_INDEX = 1000
        private const val SECOND_INDEX = 2000
        private const val THIRD_INDEX = 3000
        private const val DECRIPTION_KEY = 811589153L
    }

}