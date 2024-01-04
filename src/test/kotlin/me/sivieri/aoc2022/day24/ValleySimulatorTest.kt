package me.sivieri.aoc2022.day24

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class ValleySimulatorTest {

    @Test
    fun `part 1 example`() {
        val input = """
            #.######
            #>>.<^<#
            #.<..<<#
            #>v.><>#
            #<^v^^>#
            ######.#
        """.trimIndent()
        val simulator = ValleySimulator(input)
        val path = simulator.findThePath(true)
        assertThat(path, `is`(18))
    }

}