package me.sivieri.aoc2022.day15

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

}