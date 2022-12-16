package me.sivieri.aoc2022.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class RegolithCaveTest {

    @Test
    fun `check string representation`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        val expected = """
            ......+...
            ..........
            ..........
            ..........
            ....#...##
            ....#...#.
            ..###...#.
            ........#.
            ........#.
            #########.
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `sand unit 1` () {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        assertThat(cave.addSandUnit(), `is`(true))
        val expected = """
            ......+...
            ..........
            ..........
            ..........
            ....#...##
            ....#...#.
            ..###...#.
            ........#.
            ......o.#.
            #########.
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `sand unit 25` () {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        (1..24).forEach { _ -> cave.addSandUnit() }
        assertThat(cave.addSandUnit(), `is`(false))
        val expected = """
            ......+...
            ..........
            ......o...
            .....ooo..
            ....#ooo##
            ...o#ooo#.
            ..###ooo#.
            ....oooo#.
            .o.ooooo#.
            #########.
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `part 1 example`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        assertThat(cave.fillSandUnits(), `is`(24))
    }

}