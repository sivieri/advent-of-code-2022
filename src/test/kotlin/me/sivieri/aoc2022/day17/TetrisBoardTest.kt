package me.sivieri.aoc2022.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TetrisBoardTest {

    @Test
    fun `line piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(1), `is`(1))
    }

    @Test
    fun `plus piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(PlusPiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(1), `is`(3))
    }

    @Test
    fun `L piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(ElPiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(1), `is`(3))
    }

    @Test
    fun `pipe piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(PipePiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(1), `is`(4))
    }

    @Test
    fun `square piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(SquarePiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(1), `is`(2))
    }

    @Test
    fun `single piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = List(5) { LinePiece }
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(5), `is`(5))
    }

    @Test
    fun `two pieces down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(2), `is`(4))
    }

    @Test
    fun `multiple pieces down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(5), `is`(9))
    }

    @Test
    fun `multiple pieces down multiple times`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(10), `is`(17))
    }

    @Test
    fun `part 1 example`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        assertThat(board.calculateMaxHeight(2022), `is`(3068))
    }

}