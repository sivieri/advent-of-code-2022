package me.sivieri.aoc2022.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TetrisBoardTest {

    @Test
    fun `line piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val board = TetrisBoard(input)
        assertThat(board.calculateMaxHeight(1), `is`(1))
    }

    @Test
    fun `plus piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val board = TetrisBoard(input)
        assertThat(board.calculateMaxHeight(2), `is`(4))
    }

    @Test
    fun `L piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val board = TetrisBoard(input)
        assertThat(board.calculateMaxHeight(3), `is`(6))
    }

    @Test
    fun `pipe piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val board = TetrisBoard(input)
        assertThat(board.calculateMaxHeight(4), `is`(7))
    }

    @Test
    fun `square piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val board = TetrisBoard(input)
        assertThat(board.calculateMaxHeight(5), `is`(9))
    }

    @Test
    fun `part 1 example`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val board = TetrisBoard(input)
        assertThat(board.calculateMaxHeight(2022), `is`(3068))
    }

}