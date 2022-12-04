package me.sivieri.aoc2022.day4

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class CampCleaningTest {

    @Test
    fun `part 1 example`() {
        val input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
        """.trimIndent().split("\n").map { it.trim() }
        val campCleaning = CampCleaning(input)
        val result = campCleaning.countCompleteOverlapInPairs()
        assertThat(result, `is`(2))
    }

}