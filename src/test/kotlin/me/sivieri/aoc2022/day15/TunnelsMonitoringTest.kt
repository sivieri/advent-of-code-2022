package me.sivieri.aoc2022.day15

import me.sivieri.aoc2022.common.Coordinate2D
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class TunnelsMonitoringTest {

    @Test
    fun `parsing test`() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent().split("\n").map { it.trim() }
        val tunnelsMonitoring = TunnelsMonitoring(input)
        val expected = """
            ....S.......................
            ......................S.....
            ...............S............
            ................SB..........
            ............................
            ............................
            ............................
            ..........S.......S.........
            ............................
            ............................
            ....B.......................
            ..S.........................
            ............................
            ............................
            ..............S.......S.....
            B...........................
            ...........SB...............
            ................S..........B
            ....S.......................
            ............................
            ............S......S........
            ............................
            .......................B....
        """.trimIndent()
        assertThat(tunnelsMonitoring.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `part 1 example`() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent().split("\n").map { it.trim() }
        val tunnelsMonitoring = TunnelsMonitoring(input)
        assertThat(tunnelsMonitoring.countEmptyPositions(10), `is`(26))
    }

    @Test
    fun `find all neighbors`() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent().split("\n").map { it.trim() }
        val tunnelsMonitoring = TunnelsMonitoring(input)
        val center = Coordinate2D(8, 7)
        val result = tunnelsMonitoring.findAllNeighbors(center, 9, -20, 40)
        val expected = listOf(
            Coordinate2D(8, -2),
            Coordinate2D(9, -1),
            Coordinate2D(10, 0),
            Coordinate2D(11, 1),
            Coordinate2D(12, 2),
            Coordinate2D(13, 3),
            Coordinate2D(14, 4),
            Coordinate2D(15, 5),
            Coordinate2D(16, 6),
            Coordinate2D(17, 7),
            Coordinate2D(16, 8),
            Coordinate2D(15, 9),
            Coordinate2D(14, 10),
            Coordinate2D(13, 11),
            Coordinate2D(12, 12),
            Coordinate2D(11, 13),
            Coordinate2D(10, 14),
            Coordinate2D(9, 15),
            Coordinate2D(8, 16),
            Coordinate2D(7, 15),
            Coordinate2D(6, 14),
            Coordinate2D(5, 13),
            Coordinate2D(4, 12),
            Coordinate2D(3, 11),
            // Coordinate2D(2, 10), BEACON
            Coordinate2D(1, 9),
            Coordinate2D(0, 8),
            Coordinate2D(-1, 7),
            Coordinate2D(0, 6),
            Coordinate2D(1, 5),
            Coordinate2D(2, 4),
            Coordinate2D(3, 3),
            Coordinate2D(4, 2),
            Coordinate2D(5, 1),
            Coordinate2D(6, 0),
            Coordinate2D(7, -1)
        )
        assertThat(result, containsInAnyOrder(*expected.toTypedArray()))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
        """.trimIndent().split("\n").map { it.trim() }
        val tunnelsMonitoring = TunnelsMonitoring(input)
        assertThat(tunnelsMonitoring.findDistressBeacon(0, 20), `is`(56000011))
    }

}