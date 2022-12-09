package me.sivieri.aoc2022.day9

import me.sivieri.aoc2022.common.Coordinate2D
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers.`is`
import org.junit.Test

class LongRopeBridgeTest {

    @Test
    fun `part 2 example 1`() {
        val input = """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
        """.trimIndent().split("\n").map { RopeMovement.parseLine(it.trim()) }
        val ropeBridge = LongRopeBridge(9, Coordinate2D(0, 0), Coordinate2D(0, 0))
        ropeBridge.move(input)
        MatcherAssert.assertThat(ropeBridge.countTailPositions(), `is`(1))
    }

    @Test
    fun `part 2 example 2`() {
        val input = """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
        """.trimIndent().split("\n").map { RopeMovement.parseLine(it.trim()) }
        val ropeBridge = LongRopeBridge(9, Coordinate2D(0, 0), Coordinate2D(0, 0))
        ropeBridge.move(input)
        MatcherAssert.assertThat(ropeBridge.countTailPositions(), `is`(36))
    }

}