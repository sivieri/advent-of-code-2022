package me.sivieri.aoc2022.day2

import java.lang.IllegalArgumentException

enum class RPSMove(
    val letter: Char,
    val shapePoints: Int
) {

    Rock('A', 1),
    Paper('B', 2),
    Scissors('C', 3);

    companion object {
        fun fromLetter(letter: Char) = when (letter) {
            Rock.letter -> Rock
            Paper.letter -> Paper
            Scissors.letter -> Scissors
            else -> throw IllegalArgumentException("Unknown letter $letter")
        }
    }

}