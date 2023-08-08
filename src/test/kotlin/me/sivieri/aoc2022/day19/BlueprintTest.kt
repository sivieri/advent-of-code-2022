package me.sivieri.aoc2022.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class BlueprintTest {

    @Test
    fun `parsing test`() {
        val data = "Blueprint 1: Each ore robot costs 2 ore. Each clay robot costs 2 ore. Each obsidian robot costs 2 ore and 17 clay. Each geode robot costs 2 ore and 10 obsidian."
        val expected = Blueprint(
            1,
            OreRobot(2),
            ClayRobot(2),
            ObsidianRobot(2, 17),
            GeodeRobot(2, 10)
        )
        assertThat(Blueprint.parse(data), `is`(expected))
    }

}