package me.sivieri.aoc2022.day3

import me.sivieri.aoc2022.halve
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class RucksackTest {

    @Test
    fun `common elements value`() {
        val (part1, part2) = "vJrwpWtwJgWrhcsFMMfFFhFp".halve()
        val rucksack = Rucksack(part1, part2)
        val commonValue = rucksack.findCommonElementsValue()
        assertThat(commonValue, `is`(16))
    }

}