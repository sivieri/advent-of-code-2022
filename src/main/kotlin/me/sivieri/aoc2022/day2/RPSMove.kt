package me.sivieri.aoc2022.day2

import java.lang.IllegalArgumentException

enum class RPSMove(
    val letter: Char,
    val shapePoints: Int
) {

    Rock('A', 1),
    Paper('B', 2),
    Scissors('C', 3);

    fun resultAgainstMove(otherMove: RPSMove): Result = when (this) {
        Rock -> when (otherMove) {
            Rock -> Result.DRAW
            Paper -> Result.LOSE
            Scissors -> Result.WIN
        }
        Paper -> when (otherMove) {
            Rock -> Result.WIN
            Paper -> Result.DRAW
            Scissors -> Result.LOSE
        }
        Scissors -> when (otherMove) {
            Rock -> Result.LOSE
            Paper -> Result.WIN
            Scissors -> Result.DRAW
        }
    }

    companion object {
        fun fromLetter(letter: Char) = when (letter) {
            Rock.letter -> Rock
            Paper.letter -> Paper
            Scissors.letter -> Scissors
            else -> throw IllegalArgumentException("Unknown letter $letter")
        }
    }

}