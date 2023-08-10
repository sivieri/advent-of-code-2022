package me.sivieri.aoc2022.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Ignore
import org.junit.Test

class BlueprintSelectorViaTimeTest {

    @Test
    @Ignore
    fun `part 1 blueprint 1`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelectorViaTime(data, ResourceOrientedConstructionPlan())
        assertThat(blueprintSelector.calculateBlueprintValue(24, 1), `is`(9))
    }

    @Test
    @Ignore
    fun `part 1 blueprint 2`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelectorViaTime(data, ResourceOrientedConstructionPlan())
        assertThat(blueprintSelector.calculateBlueprintValue(24, 2), `is`(12))
    }

    @Test
    @Ignore
    fun `part 1 example`() {
        val data = """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
        """.trimIndent().split("\n").map { it.trim() }
        val blueprintSelector = BlueprintSelectorViaTime(data, ResourceOrientedConstructionPlan())
        assertThat(blueprintSelector.calculateTotalValue(24), `is`(33))
    }

}