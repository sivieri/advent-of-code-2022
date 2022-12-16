package me.sivieri.aoc2022.day14

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Ignore
import org.junit.Test

class RegolithCaveTest {

    @Test
    fun `check string representation`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        val expected = """
            000 ......+...
            001 ..........
            002 ..........
            003 ..........
            004 ....#...##
            005 ....#...#.
            006 ..###...#.
            007 ........#.
            008 ........#.
            009 #########.
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `sand unit 1` () {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        assertThat(cave.addSandUnit(), `is`(true))
        val expected = """
            000 ......+...
            001 ..........
            002 ..........
            003 ..........
            004 ....#...##
            005 ....#...#.
            006 ..###...#.
            007 ........#.
            008 ......o.#.
            009 #########.
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `sand unit 25` () {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        (1..24).forEach { _ -> cave.addSandUnit() }
        assertThat(cave.addSandUnit(), `is`(false))
        val expected = """
            000 ......+...
            001 ..........
            002 ......o...
            003 .....ooo..
            004 ....#ooo##
            005 ...o#ooo#.
            006 ..###ooo#.
            007 ....oooo#.
            008 .o.ooooo#.
            009 #########.
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `part 1 example`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input)
        assertThat(cave.fillSandUnits(), `is`(24))
    }

    @Test
    fun `check string representation with floor`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input, withFloor = true)
        val expected = """
            000 ......+...
            001 ..........
            002 ..........
            003 ..........
            004 ....#...##
            005 ....#...#.
            006 ..###...#.
            007 ........#.
            008 ........#.
            009 #########.
            010 ..........
            011 ##########
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `enlarge left`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input, withFloor = true)
        cave.enlargeMatrix(left = true)
        val expected = """
            000 .......+...
            001 ...........
            002 ...........
            003 ...........
            004 .....#...##
            005 .....#...#.
            006 ...###...#.
            007 .........#.
            008 .........#.
            009 .#########.
            010 ...........
            011 ###########
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `enlarge right`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input, withFloor = true)
        cave.enlargeMatrix(left = false)
        val expected = """
            000 ......+....
            001 ...........
            002 ...........
            003 ...........
            004 ....#...##.
            005 ....#...#..
            006 ..###...#..
            007 ........#..
            008 ........#..
            009 #########..
            010 ...........
            011 ###########
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `sand unit 94` () {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input, withFloor = true)
        (1..93).forEach { i ->
            cave.addSandUnitToTheLimit()
        }
        assertThat(cave.addSandUnitToTheLimit(), `is`(false))
        val expected = """
            000 ...........o...........
            001 ..........ooo..........
            002 .........ooooo.........
            003 ........ooooooo........
            004 .......oo#ooo##o.......
            005 ......ooo#ooo#ooo......
            006 .....oo###ooo#oooo.....
            007 ....oooo.oooo#ooooo....
            008 ...oooooooooo#oooooo...
            009 ..ooo#########ooooooo..
            010 .ooooo.......ooooooooo.
            011 #######################
        """.trimIndent()
        assertThat(cave.stringRepresentation(), `is`(expected))
    }

    @Test
    fun `part 2 example`() {
        val input = """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
        """.trimIndent().split("\n").map { it.trim() }
        val cave = RegolithCave(input, withFloor = true)
        assertThat(cave.reallyFillSandUnits(), `is`(93))
    }

}