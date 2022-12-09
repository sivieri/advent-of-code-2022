package me.sivieri.aoc2022.day9

import me.sivieri.aoc2022.common.Coordinate2D
import me.sivieri.aoc2022.day9.RopeBridge.Companion.cellDistance
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test
import java.lang.AssertionError
import java.lang.IllegalArgumentException
import kotlin.math.exp

class RopeBridgeTest {

    @Test
    fun `cell distance of coincident`() {
        assertThat(Coordinate2D(0, 0).cellDistance(Coordinate2D(0, 0)), `is`(0))
    }

    @Test
    fun `cell distance of 1 horizontal`() {
        assertThat(Coordinate2D(0, 0).cellDistance(Coordinate2D(1, 0)), `is`(1))
    }

    @Test
    fun `cell distance of 1 vertical`() {
        assertThat(Coordinate2D(0, 0).cellDistance(Coordinate2D(0, 1)), `is`(1))
    }

    @Test
    fun `cell distance of 1 diagonal`() {
        assertThat(Coordinate2D(0, 0).cellDistance(Coordinate2D(1, 1)), `is`(1))
    }

    @Test
    fun `positions when same position`() {
        val ropeBridge = RopeBridge(Coordinate2D(0, 0), Coordinate2D(0, 0))
        ropeBridge.move(RopeMovement(RopeDirection.RIGHT, 1))
        assertThat(ropeBridge.head, `is`(Coordinate2D(1, 0)))
        assertThat(ropeBridge.tail, `is`(Coordinate2D(0, 0)))
    }

    @Test
    fun `positions when horizontal distance of 1`() {
        val ropeBridge = RopeBridge(Coordinate2D(1, 0), Coordinate2D(0, 0))
        ropeBridge.move(RopeMovement(RopeDirection.RIGHT, 1))
        assertThat(ropeBridge.head, `is`(Coordinate2D(2, 0)))
        assertThat(ropeBridge.tail, `is`(Coordinate2D(1, 0)))
    }

    @Test
    fun `positions when vertical distance of 1`() {
        val ropeBridge = RopeBridge(Coordinate2D(0, 1), Coordinate2D(0, 0))
        ropeBridge.move(RopeMovement(RopeDirection.UP, 1))
        assertThat(ropeBridge.head, `is`(Coordinate2D(0, 2)))
        assertThat(ropeBridge.tail, `is`(Coordinate2D(0, 1)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `positions when horizontal distance of 2`() {
        val ropeBridge = RopeBridge(Coordinate2D(2, 0), Coordinate2D(0, 0))
        ropeBridge.move(RopeMovement(RopeDirection.RIGHT, 1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun `positions when vertical distance of 2`() {
        val ropeBridge = RopeBridge(Coordinate2D(0, 2), Coordinate2D(0, 0))
        ropeBridge.move(RopeMovement(RopeDirection.UP, 1))
    }

    @Test
    fun `positions when diagonal direction`() {
        val ropeBridge = RopeBridge(Coordinate2D(1, 1), Coordinate2D(0, 0))
        ropeBridge.move(RopeMovement(RopeDirection.RIGHT, 1))
        assertThat(ropeBridge.head, `is`(Coordinate2D(2, 1)))
        assertThat(ropeBridge.tail, `is`(Coordinate2D(1, 1)))
    }

    @Test(expected = AssertionError::class)
    fun `positions when horizontal distance greater than 2`() {
        RopeBridge(Coordinate2D(4, 0), Coordinate2D(0, 0))
    }

    @Test(expected = AssertionError::class)
    fun `positions when vertical distance greater than 2`() {
        RopeBridge(Coordinate2D(0, 4), Coordinate2D(0, 0))
    }

    @Test
    fun `complete move`() {
        val ropeBridge = RopeBridge(Coordinate2D(0, 0), Coordinate2D(0, 0))
        ropeBridge.move(RopeMovement(RopeDirection.RIGHT, 4))
        assertThat(ropeBridge.head, `is`(Coordinate2D(4, 0)))
        assertThat(ropeBridge.tail, `is`(Coordinate2D(3, 0)))
    }
    @Test
    fun `part 1 example`() {
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
        val ropeBridge = RopeBridge(Coordinate2D(0, 0), Coordinate2D(0, 0))
        ropeBridge.move(input)
        assertThat(ropeBridge.head, `is`(Coordinate2D(2, 2)))
        assertThat(ropeBridge.tail, `is`(Coordinate2D(1, 2)))
        assertThat(ropeBridge.countTailPositions(), `is`(13))
    }


}