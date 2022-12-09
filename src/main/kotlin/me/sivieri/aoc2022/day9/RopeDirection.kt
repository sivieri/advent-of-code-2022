package me.sivieri.aoc2022.day9

enum class RopeDirection(private val id: Char) {

    LEFT('L'),
    RIGHT('R'),
    UP('U'),
    DOWN('D');

    companion object {
        fun getRopeDirection(id: Char): RopeDirection = values().find { it.id == id } ?: throw IllegalArgumentException("Unknown direction $id")
    }

}