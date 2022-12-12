package me.sivieri.aoc2022.day12

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class HillClimbingTest {

    @Test
    fun `part 1 example`() {
        val input = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent().split("\n").map { it.trim() }
        val hillClimbing = HillClimbing(input)
        assertThat(hillClimbing.findShortestPathStepNumber(), `is`(31))
    }

}