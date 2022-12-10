package me.sivieri.aoc2022.day10

import java.lang.IllegalArgumentException

class VideoInterpreter(
    input: List<String>
) {

    private var cycle: Int = 0
    private var x: Int = 1
    private val instructions: List<VideoInstruction> = input.map {
        val parts = it.split(" ")
        when (parts[0]) {
            AddX.name -> AddX(parts[1].toInt())
            Noop.name -> Noop()
            else -> throw IllegalArgumentException("Unknown instruction $it")
        }
    }
    private var currentInstruction: Int = 0

    fun findTotalSignalStrength(
        min: Int,
        step: Int,
        max: Int
    ): Int {
        var result = 0
        while (currentInstruction < instructions.size) {
            val cur = instructions[currentInstruction]
            (1..cur.cycles()).forEach { _ ->
                cycle++
                result += checkCycle(min, step,  max)
            }
            if (cur is AddX) x += cur.value!!
            currentInstruction++
        }
        return result
    }

    private fun checkCycle(min: Int, step: Int, max: Int): Int =
        if (cycle in min..max && (cycle - min) % step == 0) x * cycle
        else 0

}