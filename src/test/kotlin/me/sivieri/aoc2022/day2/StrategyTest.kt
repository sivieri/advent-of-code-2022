package me.sivieri.aoc2022.day2

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class StrategyTest {

    @Test
    fun `part 1 example`() {
        val input = """
            A Y
            B X
            C Z
        """.trimIndent()
            .split("\n")
            .map {
                val (a, b) = it.split(" ", limit = 2)
                a.first() to b.first()
            }
        val mapping = mapOf(
            'X' to RPSMove.Rock,
            'Y' to RPSMove.Paper,
            'Z' to RPSMove.Scissors
        )
        val strategy = Strategy()
        val result = strategy.resolve(input, mapping)
        assertThat(result, `is`(15))
    }

}