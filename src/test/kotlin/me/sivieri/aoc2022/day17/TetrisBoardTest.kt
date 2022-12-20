package me.sivieri.aoc2022.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class TetrisBoardTest {

    @Test
    fun `part 1 example`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val board = TetrisBoard(input)
        assertThat(board.calculateMaxHeight(2022), `is`(3068))
    }

}