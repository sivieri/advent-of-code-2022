package me.sivieri.aoc2022.day5

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.util.ArrayDeque

class Crane9001Test {

    @Test
    fun parsing() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val crane = Crane9001(input, "")
        val expectedStatus = listOf(
            CraneStack(1, ArrayDeque(listOf('N', 'Z'))),
            CraneStack(2, ArrayDeque(listOf('D', 'C', 'M'))),
            CraneStack(3, ArrayDeque(listOf('P'))),
        )
        assertThat(crane.status, `is`(expectedStatus))
    }

    @Test
    fun stringRepresentation() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val crane = Crane9001(input, "")
        assertThat(crane.toString(), `is`(input))
    }

    @Test
    fun `single step move`() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val crane = Crane9001(input, "")
        val move = CraneMove(1, 2, 3)
        crane.move(move)
        val expectedStatus = listOf(
            CraneStack(1, ArrayDeque(listOf('N', 'Z'))),
            CraneStack(2, ArrayDeque(listOf('C', 'M'))),
            CraneStack(3, ArrayDeque(listOf('D', 'P'))),
        )
        assertThat(crane.status, `is`(expectedStatus))
    }

    @Test
    fun `multiple steps move`() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val crane = Crane9001(input, "")
        val move = CraneMove(2, 2, 3)
        crane.move(move)
        val expectedStatus = listOf(
            CraneStack(1, ArrayDeque(listOf('N', 'Z'))),
            CraneStack(2, ArrayDeque(listOf('M'))),
            CraneStack(3, ArrayDeque(listOf('D', 'C', 'P'))),
        )
        assertThat(crane.status, `is`(expectedStatus))
    }

    @Test
    fun `part 2 example all moves`() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val moves = """
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """.trimIndent()
        val crane = Crane9001(input, moves)
        crane.moveAll()
        val expectedStatus = listOf(
            CraneStack(1, ArrayDeque(listOf('M'))),
            CraneStack(2, ArrayDeque(listOf('C'))),
            CraneStack(3, ArrayDeque(listOf('D', 'N', 'Z', 'P'))),
        )
        assertThat(crane.status, `is`(expectedStatus))
    }

    @Test
    fun `part 2 example final status`() {
        val input = listOf(
            "    [D]    ",
            "[N] [C]    ",
            "[Z] [M] [P]",
            " 1   2   3 "
        ).joinToString("\n")
        val moves = """
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
        """.trimIndent()
        val crane = Crane9001(input, moves)
        crane.moveAll()
        assertThat(crane.getTopStatus(), `is`("MCD"))
    }

}