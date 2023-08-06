package me.sivieri.aoc2022.day17

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Path

class TetrisBoardTest {

    @Test
    fun `line piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece)
        val board = TetrisBoard(input, pieces)
        board.play(1)
        assertThat(board.maxHeight(), `is`(1))
    }

    @Test
    fun `plus piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(PlusPiece)
        val board = TetrisBoard(input, pieces)
        board.play(1)
        assertThat(board.maxHeight(), `is`(3))
    }

    @Test
    fun `L piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(ElPiece)
        val board = TetrisBoard(input, pieces)
        board.play(1)
        assertThat(board.maxHeight(), `is`(3))
    }

    @Test
    fun `pipe piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(PipePiece)
        val board = TetrisBoard(input, pieces)
        board.play(1)
        assertThat(board.maxHeight(), `is`(4))
    }

    @Test
    fun `square piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(SquarePiece)
        val board = TetrisBoard(input, pieces)
        board.play(1)
        assertThat(board.maxHeight(), `is`(2))
    }

    @Test
    fun `single piece down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = List(5) { LinePiece }
        val board = TetrisBoard(input, pieces)
        board.play(5)
        assertThat(board.maxHeight(), `is`(5))
    }

    @Test
    fun `two pieces down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece)
        val board = TetrisBoard(input, pieces)
        board.play(2)
        assertThat(board.maxHeight(), `is`(4))
    }

    @Test
    fun `multiple pieces down`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        board.play(5)
        assertThat(board.maxHeight(), `is`(9))
    }

    @Test
    fun `multiple pieces down multiple times - height`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        board.play(10)
        assertThat(board.maxHeight(), `is`(17))
    }

    @Test
    fun `multiple pieces down multiple times - board check`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        board.play(10)
        val height = board.maxHeight()
        val expected = listOf(
            "....#..".toCharArray().toList(),
            "....#..".toCharArray().toList(),
            "....##.".toCharArray().toList(),
            "##..##.".toCharArray().toList(),
            "######.".toCharArray().toList(),
            ".###...".toCharArray().toList(),
            "..#....".toCharArray().toList(),
            ".####..".toCharArray().toList(),
            "....##.".toCharArray().toList(),
            "....##.".toCharArray().toList(),
            "....#..".toCharArray().toList(),
            "..#.#..".toCharArray().toList(),
            "..#.#..".toCharArray().toList(),
            "#####..".toCharArray().toList(),
            "..###..".toCharArray().toList(),
            "...#...".toCharArray().toList(),
            "..####.".toCharArray().toList()
        )
        val f: (List<MutableList<Char>>) -> Boolean = { status ->
            (0 until height).all { status[it] == expected[height - it - 1] }
        }
        assertThat(board.boardStatusChecker(f), `is`(true))
    }

    @Test
    fun `part 1 example`() {
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        board.play(2022)
        assertThat(board.maxHeight(), `is`(3068))
    }

    @Test
    fun `part 1 example - heights check`() {
        val heights = Files
            .lines(Path.of(this::class.java.getResource("/day17/heights.txt")!!.toURI()))
            .iterator()
            .asSequence()
            .toList()
            .map { it.toInt() }
        val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"
        val pieces = listOf(LinePiece, PlusPiece, ElPiece, PipePiece, SquarePiece)
        val board = TetrisBoard(input, pieces)
        board.playWithCollateral(2022) { i, _, height ->
            assertThat(height, `is`(heights[i - 1]))
        }
    }

}