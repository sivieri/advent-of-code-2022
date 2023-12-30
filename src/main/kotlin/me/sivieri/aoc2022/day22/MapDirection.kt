package me.sivieri.aoc2022.day22

enum class MapDirection(val symbol: Char, val facing: Int) {
    RIGHT('>', 0),
    LEFT('<', 2),
    UP('^', 3),
    DOWN('v', 1);

    fun rotate(direction: MapInstruction): MapDirection = when (direction) {
        is RotateClockwiseInstruction -> {
            val newEntry = (this.facing + 1) % entries.size
            entries.find { it.facing == newEntry }!!
        }
        is RotateCounterClockwiseInstruction -> {
            val newEntry = if (this.facing - 1 < 0) entries.size - 1 else this.facing - 1
            entries.find { it.facing == newEntry }!!
        }
        is MoveInstruction -> throw IllegalArgumentException("Move cannot be used to change direction")
    }

    fun opposite(): MapDirection = when (this) {
        RIGHT -> LEFT
        LEFT -> RIGHT
        UP -> DOWN
        DOWN -> UP
    }
}