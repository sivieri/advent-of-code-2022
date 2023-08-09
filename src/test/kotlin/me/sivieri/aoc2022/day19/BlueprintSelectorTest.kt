package me.sivieri.aoc2022.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
import org.junit.Test

class BlueprintSelectorTest {

    @Test
    fun `part 1 blueprint 1`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data, PrecedenceConstructionPlan())
        assertThat(blueprintSelector.calculateBlueprintValue(24, 1), `is`(9))
    }

    @Test
    @Ignore
    fun `part 1 blueprint 2`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data, PrecedenceConstructionPlan())
        assertThat(blueprintSelector.calculateBlueprintValue(24, 2), `is`(24))
    }

    @Test
    @Ignore
    fun `part 1 map`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data, PrecedenceConstructionPlan())
        assertThat(blueprintSelector.calculateBlueprintsValue(24), `is`(mapOf(1 to 9, 2 to 24)))
    }

    @Test
    @Ignore
    fun `part 1 total value`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelector(data, PrecedenceConstructionPlan())
        assertThat(blueprintSelector.calculateTotalValue(24), `is`(33))
    }

}