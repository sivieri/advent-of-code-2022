package me.sivieri.aoc2022.day23

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class GroveExplorationTest {

    @Test
    fun `part 1 example 1`() {
        val input = """
            .....
            ..##.
            ..#..
            .....
            ..##.
            .....
        """.trimIndent()
        val grove = GroveExploration(input)
        val result = grove.countEmptyGround(10)
        assertThat(result, `is`(25))
    }

    @Test
    fun `part 1 example 2`() {
        val input = """
            ....#..
            ..###.#
            #...#.#
            .#...##
            #.###..
            ##.#.##
            .#..#..
        """.trimIndent()
        val grove = GroveExploration(input)
        val result = grove.countEmptyGround(10)
        assertThat(result, `is`(110))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            ....#..
            ..###.#
            #...#.#
            .#...##
            #.###..
            ##.#.##
            .#..#..
        """.trimIndent()
        val grove = GroveExploration(input)
        val result = grove.countRounds()
        assertThat(result, `is`(20))
    }

}