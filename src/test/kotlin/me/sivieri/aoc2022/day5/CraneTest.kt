package me.sivieri.aoc2022.day5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.util.ArrayDeque

class CraneTest {

    @Test
    fun parsing() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val crane = Crane(input, "")
        val expectedStatus = listOf(
            CraneStack(1, ArrayDeque(listOf('N', 'Z'))),
            CraneStack(2, ArrayDeque(listOf('D', 'C', 'M'))),
            CraneStack(3, ArrayDeque(listOf('P'))),
        )
        assertThat(crane.status, `is`(expectedStatus))
    }

    @Test
    fun stringRepresentation() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val crane = Crane(input, "")
        assertThat(crane.toString(), `is`(input))
    }

}