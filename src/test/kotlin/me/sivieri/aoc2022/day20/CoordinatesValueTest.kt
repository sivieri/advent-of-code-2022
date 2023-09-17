package me.sivieri.aoc2022.day20

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CoordinatesValueTest {

    @Test
    fun `get index by position`() {
        val data = arrayOf(
            CoordinatesValue(1, 0),
            CoordinatesValue(2, 1),
            CoordinatesValue(-3, 2),
            CoordinatesValue(3, 3),
            CoordinatesValue(-2, 4),
            CoordinatesValue(0, 5),
            CoordinatesValue(4, 6)
        )
        val result = data.indexOfByPosition(4)
        assertThat(result, `is`(4))
    }

    @Test
    fun `get index by value`() {
        val data = arrayOf(
            CoordinatesValue(1, 0),
            CoordinatesValue(2, 1),
            CoordinatesValue(-3, 2),
            CoordinatesValue(3, 3),
            CoordinatesValue(-2, 4),
            CoordinatesValue(0, 5),
            CoordinatesValue(4, 6)
        )
        val result = data.indexOfByValue(-2)
        assertThat(result, `is`(4))
    }

}