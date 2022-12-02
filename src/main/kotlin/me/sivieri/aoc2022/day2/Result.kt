package me.sivieri.aoc2022.day2

import java.lang.IllegalArgumentException

enum class Result(
    val letter: Char,
    val points: Int
) {

    WIN('Z', 6),
    DRAW('Y', 3),
    LOSE('X', 0);

    fun otherMove(theirMove: RPSMove): RPSMove = when (this) {
        WIN -> when (theirMove) {
            RPSMove.Rock -> RPSMove.Paper
            RPSMove.Paper -> RPSMove.Scissors
            RPSMove.Scissors -> RPSMove.Rock
        }
        DRAW -> when (theirMove) {
            RPSMove.Rock -> RPSMove.Rock
            RPSMove.Paper -> RPSMove.Paper
            RPSMove.Scissors -> RPSMove.Scissors
        }
        LOSE -> when (theirMove) {
            RPSMove.Rock -> RPSMove.Scissors
            RPSMove.Paper -> RPSMove.Rock
            RPSMove.Scissors -> RPSMove.Paper
        }
    }

    companion object {
        fun fromLetter(letter: Char): Result = when (letter) {
            WIN.letter -> WIN
            DRAW.letter -> DRAW
            LOSE.letter -> LOSE
            else -> throw IllegalArgumentException("Unknown letter $letter")
        }
    }

}