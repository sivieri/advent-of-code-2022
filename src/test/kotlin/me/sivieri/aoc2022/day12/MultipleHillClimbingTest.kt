package me.sivieri.aoc2022.day12

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class MultipleHillClimbingTest {

    @Test
    fun `part 2 example`() {
        val input = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent().split("\n").map { it.trim() }
        val hillClimbing = MultipleHillClimbing(input)
        assertThat(hillClimbing.findShortestPathStepNumber(), `is`(29))
    }

}