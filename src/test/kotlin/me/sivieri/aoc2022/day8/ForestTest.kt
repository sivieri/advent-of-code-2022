package me.sivieri.aoc2022.day8

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class ForestTest {

    private val input = """
            30373
            25512
            65332
            33549
            35390
        """.trimIndent().split("\n").map { it.trim() }
    private val forest = Forest(input)

    @Test
    fun `get all elements from left`() {
        assertThat(forest.getAllElements(3, 1, Direction.LEFT), `is`(listOf(2, 5, 5)))
    }

    @Test
    fun `get all elements from right`() {
        assertThat(forest.getAllElements(3, 1, Direction.RIGHT), `is`(listOf(2)))
    }

    @Test
    fun `get all elements from top`() {
        assertThat(forest.getAllElements(3, 1, Direction.TOP), `is`(listOf(7)))
    }

    @Test
    fun `get all elements from bottom`() {
        assertThat(forest.getAllElements(3, 1, Direction.BOTTOM), `is`(listOf(3, 4, 9)))
    }

    @Test
    fun `part 1 example`() {
        assertThat(forest.countVisibleTrees(), `is`(21))
    }

}