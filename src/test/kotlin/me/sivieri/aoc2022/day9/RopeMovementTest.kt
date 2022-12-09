package me.sivieri.aoc2022.day9

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class RopeMovementTest {

    @Test
    fun `correctly parse a line`() {
        val line = "R 5"
        val mov = RopeMovement.parseLine(line)
        assertThat(mov, `is`(RopeMovement(RopeDirection.RIGHT, 5)))
    }

}