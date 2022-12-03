package me.sivieri.aoc2022.day3

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class RucksackOrderingTest {

    @Test
    fun `part 1 example`() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent().split("\n").map { it.trim() }
        val rucksackOrdering = RucksackOrdering(input)
        val result = rucksackOrdering.findGlobalCommonValue()
        assertThat(result, `is`(157))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
        """.trimIndent().split("\n").map { it.trim() }
        val rucksackOrdering = RucksackOrdering(input)
        val result = rucksackOrdering.findBadgesValue()
        assertThat(result, `is`(70))
    }

}