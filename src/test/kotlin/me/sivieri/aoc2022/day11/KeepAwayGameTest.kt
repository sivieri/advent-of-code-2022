package me.sivieri.aoc2022.day11

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Test

class KeepAwayGameTest {

    @Test
    fun `part 1 example initial status`() {
        val game = KeepAwayGame(part1)
        val expected = """
            Monkey 0: 79, 98
            Monkey 1: 54, 65, 75, 74
            Monkey 2: 79, 60, 97
            Monkey 3: 74
        """.trimIndent()
        assertThat(game.monkeyItemsRepresentation(), `is`(expected))
    }

    @Test
    fun `part 1 example single round`() {
        val game = KeepAwayGame(part1)
        game.play(1, 3)
        val expected = """
            Monkey 0: 20, 23, 27, 26
            Monkey 1: 2080, 25, 167, 207, 401, 1046
            Monkey 2: 
            Monkey 3: 
        """.trimIndent()
        assertThat(game.monkeyItemsRepresentation(), `is`(expected))
    }

    @Test
    fun `part 1 example 20 rounds`() {
        val game = KeepAwayGame(part1)
        game.play(20, 3)
        val expected = """
            Monkey 0: 10, 12, 14, 26, 34
            Monkey 1: 245, 93, 53, 199, 115
            Monkey 2: 
            Monkey 3: 
        """.trimIndent()
        assertThat(game.monkeyItemsRepresentation(), `is`(expected))
    }

    @Test
    fun `part 1 example monkey business after 20 rounds`() {
        val game = KeepAwayGame(part1)
        game.play(20, 3)
        val monkeyBusiness = game.calculateMonkeyBusiness()
        assertThat(monkeyBusiness, `is`(10605L))
    }

    @Test
    fun `part 2 example single round`() {
        val game = KeepAwayGame(part1)
        game.play(1)
        val expected = """
            Monkey 0 inspected items 2 times.
            Monkey 1 inspected items 4 times.
            Monkey 2 inspected items 3 times.
            Monkey 3 inspected items 6 times.
        """.trimIndent()
        assertThat(game.monkeyInspectionsRepresentation(), `is`(expected))
    }

    @Test
    fun `part 2 example 20 rounds`() {
        val game = KeepAwayGame(part1)
        game.play(20)
        val expected = """
            Monkey 0 inspected items 99 times.
            Monkey 1 inspected items 97 times.
            Monkey 2 inspected items 8 times.
            Monkey 3 inspected items 103 times.
        """.trimIndent()
        assertThat(game.monkeyInspectionsRepresentation(), `is`(expected))
    }

    @Test
    fun `part 2 example monkey business after 10000 rounds`() {
        val game = KeepAwayGame(part1)
        game.play(10000)
        val monkeyBusiness = game.calculateMonkeyBusiness()
        assertThat(monkeyBusiness, `is`(2713310158L))
    }

    companion object {
        private val part1 = """
Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1
        """.split("\n\n")
    }

}