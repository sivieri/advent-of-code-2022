package me.sivieri.aoc2022.day24

import java.lang.IllegalArgumentException

enum class BlizzardDirection(val symbol: Char) {
    UP('^'),
    DOWN('v'),
    LEFT('<'),
    RIGHT('>');

    companion object {
        fun fromSymbol(symbol: Char): BlizzardDirection = when (symbol) {
            UP.symbol -> UP
            DOWN.symbol -> DOWN
            LEFT.symbol -> LEFT
            RIGHT.symbol -> RIGHT
            else -> throw IllegalArgumentException("$symbol is not valid")
        }
    }
}