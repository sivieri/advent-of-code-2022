package me.sivieri.aoc2022.day25

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BobTest {

    @Test
    fun `part 1 example`() {
        val input = """
            1=-0-2
            12111
            2=0=
            21
            2=01
            111
            20012
            112
            1=-1=
            1-12
            12
            1=
            122
        """.trimIndent().split("\n")
        val bob = Bob(input)
        val result = bob.calculateTotal()
        assertThat(result, `is`(4890))
    }

}