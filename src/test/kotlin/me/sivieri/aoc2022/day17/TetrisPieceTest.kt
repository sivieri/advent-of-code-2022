package me.sivieri.aoc2022.day17

import me.sivieri.aoc2022.common.Coordinate2D
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.containsInAnyOrder
import org.junit.Test

class TetrisPieceTest {

    @Test
    fun `line test`() {
        val start = Coordinate2D(2, 3)
        val expected = listOf(
            Coordinate2D(2, 3),
            Coordinate2D(3, 3),
            Coordinate2D(4, 3),
            Coordinate2D(5, 3)
        ).toTypedArray()
        assertThat(LinePiece.generateFigure(start), containsInAnyOrder(*expected))
    }

    @Test
    fun `plus test`() {
        val start = Coordinate2D(2, 3)
        val expected = listOf(
            Coordinate2D(3, 3),
            Coordinate2D(3, 4),
            Coordinate2D(3, 5),
            Coordinate2D(2, 4),
            Coordinate2D(4, 4)
        ).toTypedArray()
        assertThat(PlusPiece.generateFigure(start), containsInAnyOrder(*expected))
    }

    @Test
    fun `L test`() {
        val start = Coordinate2D(2, 3)
        val expected = listOf(
            Coordinate2D(2, 3),
            Coordinate2D(3, 3),
            Coordinate2D(4, 3),
            Coordinate2D(4, 4),
            Coordinate2D(4, 5)
        ).toTypedArray()
        assertThat(ElPiece.generateFigure(start), containsInAnyOrder(*expected))
    }

    @Test
    fun `pipe test`() {
        val start = Coordinate2D(2, 3)
        val expected = listOf(
            Coordinate2D(2, 3),
            Coordinate2D(2, 4),
            Coordinate2D(2, 5),
            Coordinate2D(2, 6),
        ).toTypedArray()
        assertThat(PipePiece.generateFigure(start), containsInAnyOrder(*expected))
    }
    @Test
    fun `square test`() {
        val start = Coordinate2D(2, 3)
        val expected = listOf(
            Coordinate2D(2, 3),
            Coordinate2D(3, 3),
            Coordinate2D(2, 4),
            Coordinate2D(3, 4),
        ).toTypedArray()
        assertThat(SquarePiece.generateFigure(start), containsInAnyOrder(*expected))
    }

}