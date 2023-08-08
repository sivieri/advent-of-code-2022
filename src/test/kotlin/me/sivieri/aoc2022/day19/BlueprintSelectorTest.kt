package me.sivieri.aoc2022.day19

import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Test

class BlueprintSelectorTest {

    @Test
    fun `part 1 blueprint 1`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data)
        MatcherAssert.assertThat(blueprintSelector.calculateBlueprintValue(24, 1), Matchers.`is`(9))
    }

    @Test
    fun `part 1 blueprint 2`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data)
        MatcherAssert.assertThat(blueprintSelector.calculateBlueprintValue(24, 2), Matchers.`is`(24))
    }

    @Test
    fun `part 1 map`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data)
        MatcherAssert.assertThat(blueprintSelector.calculateBlueprintsValue(24), Matchers.`is`(mapOf(1 to 9, 2 to 24)))
    }

    @Test
    fun `part 1 total value`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data)
        MatcherAssert.assertThat(blueprintSelector.calculateTotalValue(24), Matchers.`is`(33))
    }

}