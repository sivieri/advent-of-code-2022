package me.sivieri.aoc2022.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class RangeTest {

    @Test
    fun `contains a contained`() {
        assertThat(Range(2, 7).fullyContains(Range(3, 6)), `is`(true))
    }

    @Test
    fun `contains an overlapping on the left`() {
        assertThat(Range(2, 7).fullyContains(Range(1, 4)), `is`(false))
    }

    @Test
    fun `contains an overlapping on the right`() {
        assertThat(Range(2, 7).fullyContains(Range(4, 9)), `is`(false))
    }

    @Test
    fun `contains an external on the left`() {
        assertThat(Range(2, 7).fullyContains(Range(0, 1)), `is`(false))
    }

    @Test
    fun `contains an external on the right`() {
        assertThat(Range(2, 7).fullyContains(Range(8, 10)), `is`(false))
    }

}