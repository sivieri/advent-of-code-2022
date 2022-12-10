package me.sivieri.aoc2022.day10

sealed class VideoInstruction(val value: Int?) {
    abstract fun cycles(): Int
    abstract fun execute(currentValue: Int): Int
}

class AddX(value: Int): VideoInstruction(value) {
    override fun cycles(): Int = 2
    override fun execute(currentValue: Int): Int = currentValue + value!!
    companion object {
        const val name = "addx"
    }
}

class Noop: VideoInstruction(null) {
    override fun cycles(): Int = 1
    override fun execute(currentValue: Int): Int = currentValue
    companion object {
        const val name = "noop"
    }
}
