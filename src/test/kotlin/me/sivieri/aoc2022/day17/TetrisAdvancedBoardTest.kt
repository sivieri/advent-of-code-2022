package me.sivieri.aoc2022.day17

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Ignore
import org.junit.Test

class TetrisAdvancedBoardTest {

    @Test
    fun `part 2 example`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisAdvancedBoard(input, pieces)
        board.play(1_000_000_000_000)
        MatcherAssert.assertThat(board.maxHeight, Matchers.`is`(1_514_285_714_288))
    }

}