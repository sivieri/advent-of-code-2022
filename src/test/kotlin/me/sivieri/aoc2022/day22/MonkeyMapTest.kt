package me.sivieri.aoc2022.day22

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.day22.MonkeyMap.Companion.moveDown
import me.sivieri.aoc2022.day22.MonkeyMap.Companion.moveLeft
import me.sivieri.aoc2022.day22.MonkeyMap.Companion.moveRight
import me.sivieri.aoc2022.day22.MonkeyMap.Companion.moveUp
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class MonkeyMapTest {

    @Test
    fun `part 1 example`() {
        val input = """
        ...#
        .#..
        #...
        ....
...#.......#
........#...
..#....#....
..........#.
        ...#....
        .....#..
        .#......
        ......#.

10R5L5R10L4R5L5
        """
        val map = MonkeyMap(input)
        val result = map.play(true)
        assertThat(result, `is`(6032))
    }

    @Test
    fun `move right example 1`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(1, 0) to MonkeyMap.NOTHING,
            Coordinate2D(2, 0) to MonkeyMap.NOTHING,
            Coordinate2D(3, 0) to MonkeyMap.NOTHING,
            Coordinate2D(4, 0) to MonkeyMap.EMPTY,
            Coordinate2D(5, 0) to MonkeyMap.WALL,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(7, 0).moveRight(board, 8)
        val expected = Coordinate2D(4, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move right example 2`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.EMPTY,
            Coordinate2D(1, 0) to MonkeyMap.EMPTY,
            Coordinate2D(2, 0) to MonkeyMap.EMPTY,
            Coordinate2D(3, 0) to MonkeyMap.EMPTY,
            Coordinate2D(4, 0) to MonkeyMap.EMPTY,
            Coordinate2D(5, 0) to MonkeyMap.WALL,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(7, 0).moveRight(board, 8)
        val expected = Coordinate2D(0, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move right example 3`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(1, 0) to MonkeyMap.NOTHING,
            Coordinate2D(2, 0) to MonkeyMap.NOTHING,
            Coordinate2D(3, 0) to MonkeyMap.NOTHING,
            Coordinate2D(4, 0) to MonkeyMap.WALL,
            Coordinate2D(5, 0) to MonkeyMap.EMPTY,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(7, 0).moveRight(board, 8)
        val expected = Coordinate2D(7, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move right example 4`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.WALL,
            Coordinate2D(1, 0) to MonkeyMap.EMPTY,
            Coordinate2D(2, 0) to MonkeyMap.EMPTY,
            Coordinate2D(3, 0) to MonkeyMap.EMPTY,
            Coordinate2D(4, 0) to MonkeyMap.WALL,
            Coordinate2D(5, 0) to MonkeyMap.EMPTY,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(7, 0).moveRight(board, 8)
        val expected = Coordinate2D(7, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move left example 1`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(1, 0) to MonkeyMap.NOTHING,
            Coordinate2D(2, 0) to MonkeyMap.NOTHING,
            Coordinate2D(3, 0) to MonkeyMap.NOTHING,
            Coordinate2D(4, 0) to MonkeyMap.EMPTY,
            Coordinate2D(5, 0) to MonkeyMap.WALL,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(4, 0).moveLeft(board, 8)
        val expected = Coordinate2D(7, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move left example 2`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.EMPTY,
            Coordinate2D(1, 0) to MonkeyMap.EMPTY,
            Coordinate2D(2, 0) to MonkeyMap.EMPTY,
            Coordinate2D(3, 0) to MonkeyMap.EMPTY,
            Coordinate2D(4, 0) to MonkeyMap.EMPTY,
            Coordinate2D(5, 0) to MonkeyMap.WALL,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(0, 0).moveLeft(board, 8)
        val expected = Coordinate2D(7, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move left example 3`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(1, 0) to MonkeyMap.NOTHING,
            Coordinate2D(2, 0) to MonkeyMap.NOTHING,
            Coordinate2D(3, 0) to MonkeyMap.NOTHING,
            Coordinate2D(4, 0) to MonkeyMap.EMPTY,
            Coordinate2D(5, 0) to MonkeyMap.EMPTY,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.WALL,
        )
        val result = Coordinate2D(4, 0).moveLeft(board, 8)
        val expected = Coordinate2D(4, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move left example 4`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.EMPTY,
            Coordinate2D(1, 0) to MonkeyMap.EMPTY,
            Coordinate2D(2, 0) to MonkeyMap.EMPTY,
            Coordinate2D(3, 0) to MonkeyMap.EMPTY,
            Coordinate2D(4, 0) to MonkeyMap.WALL,
            Coordinate2D(5, 0) to MonkeyMap.EMPTY,
            Coordinate2D(6, 0) to MonkeyMap.EMPTY,
            Coordinate2D(7, 0) to MonkeyMap.WALL,
        )
        val result = Coordinate2D(0, 0).moveLeft(board, 8)
        val expected = Coordinate2D(0, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move down example 1`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(0, 1) to MonkeyMap.NOTHING,
            Coordinate2D(0, 2) to MonkeyMap.NOTHING,
            Coordinate2D(0, 3) to MonkeyMap.NOTHING,
            Coordinate2D(0, 4) to MonkeyMap.EMPTY,
            Coordinate2D(0, 5) to MonkeyMap.WALL,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(0, 7).moveDown(board, 8)
        val expected = Coordinate2D(0, 4)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move down example 2`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.EMPTY,
            Coordinate2D(0, 1) to MonkeyMap.EMPTY,
            Coordinate2D(0, 2) to MonkeyMap.EMPTY,
            Coordinate2D(0, 3) to MonkeyMap.EMPTY,
            Coordinate2D(0, 4) to MonkeyMap.EMPTY,
            Coordinate2D(0, 5) to MonkeyMap.WALL,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(0, 7).moveDown(board, 8)
        val expected = Coordinate2D(0, 0)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move down example 3`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(0, 1) to MonkeyMap.NOTHING,
            Coordinate2D(0, 2) to MonkeyMap.NOTHING,
            Coordinate2D(0, 3) to MonkeyMap.NOTHING,
            Coordinate2D(0, 4) to MonkeyMap.WALL,
            Coordinate2D(0, 5) to MonkeyMap.EMPTY,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(0, 7).moveDown(board, 8)
        val expected = Coordinate2D(0, 7)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move down example 4`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.WALL,
            Coordinate2D(0, 1) to MonkeyMap.EMPTY,
            Coordinate2D(0, 2) to MonkeyMap.EMPTY,
            Coordinate2D(0, 3) to MonkeyMap.EMPTY,
            Coordinate2D(0, 4) to MonkeyMap.EMPTY,
            Coordinate2D(0, 5) to MonkeyMap.WALL,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(0, 7).moveDown(board, 8)
        val expected = Coordinate2D(0, 7)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move up example 1`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(0, 1) to MonkeyMap.NOTHING,
            Coordinate2D(0, 2) to MonkeyMap.NOTHING,
            Coordinate2D(0, 3) to MonkeyMap.NOTHING,
            Coordinate2D(0, 4) to MonkeyMap.EMPTY,
            Coordinate2D(0, 5) to MonkeyMap.WALL,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(0, 4).moveUp(board, 8)
        val expected = Coordinate2D(0, 7)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move up example 2`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.EMPTY,
            Coordinate2D(0, 1) to MonkeyMap.EMPTY,
            Coordinate2D(0, 2) to MonkeyMap.EMPTY,
            Coordinate2D(0, 3) to MonkeyMap.EMPTY,
            Coordinate2D(0, 4) to MonkeyMap.EMPTY,
            Coordinate2D(0, 5) to MonkeyMap.WALL,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.EMPTY,
        )
        val result = Coordinate2D(0, 0).moveUp(board, 8)
        val expected = Coordinate2D(0, 7)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move up example 3`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.NOTHING,
            Coordinate2D(0, 1) to MonkeyMap.NOTHING,
            Coordinate2D(0, 2) to MonkeyMap.NOTHING,
            Coordinate2D(0, 3) to MonkeyMap.NOTHING,
            Coordinate2D(0, 4) to MonkeyMap.EMPTY,
            Coordinate2D(0, 5) to MonkeyMap.EMPTY,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.WALL,
        )
        val result = Coordinate2D(0, 4).moveUp(board, 8)
        val expected = Coordinate2D(0, 4)
        assertThat(result, `is`(expected))
    }

    @Test
    fun `move up example 4`() {
        val board = mapOf(
            Coordinate2D(0, 0) to MonkeyMap.EMPTY,
            Coordinate2D(0, 1) to MonkeyMap.EMPTY,
            Coordinate2D(0, 2) to MonkeyMap.EMPTY,
            Coordinate2D(0, 3) to MonkeyMap.EMPTY,
            Coordinate2D(0, 4) to MonkeyMap.EMPTY,
            Coordinate2D(0, 5) to MonkeyMap.WALL,
            Coordinate2D(0, 6) to MonkeyMap.EMPTY,
            Coordinate2D(0, 7) to MonkeyMap.WALL,
        )
        val result = Coordinate2D(0, 0).moveUp(board, 8)
        val expected = Coordinate2D(0, 0)
        assertThat(result, `is`(expected))
    }

}