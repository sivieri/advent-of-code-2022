package me.sivieri.aoc2022.day19

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test

class ConstructionPlanTest {

    private val blueprint = Blueprint(
        1,
        OreRobot(2),
        ClayRobot(3),
        ObsidianRobot(3, 17),
        GeodeRobot(3, 10)
    )

    @Test
    fun `basic plan test with initial status`() {
        val status = ExtractionStatus()
        val plan = BasicConstructionPlan()
        val result = plan.executePlan(blueprint, status)
        val expected = listOf(Pair(status, null))
        assertThat(result, `is`(expected))
    }

    @Test
    fun `basic plan test with full status`() {
        val status = ExtractionStatus(1, 1, 1, 1, 3, 17, 10, 0)
        val plan = BasicConstructionPlan()
        val result = plan.executePlan(blueprint, status)
        val expected = listOf(
            Pair(ExtractionStatus(1, 1, 1, 1, 1, 17, 10, 0), blueprint.oreRobot),
            Pair(ExtractionStatus(1, 1, 1, 1, 0, 17, 10, 0), blueprint.clayRobot),
            Pair(ExtractionStatus(1, 1, 1, 1, 0, 0, 10, 0), blueprint.obsidianRobot),
            Pair(ExtractionStatus(1, 1, 1, 1, 0, 17, 0, 0), blueprint.geodeRobot),
            Pair(status, null)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `precedence plan test with full status`() {
        val status = ExtractionStatus(1, 1, 1, 1, 3, 17, 10, 0)
        val plan = PrecedenceConstructionPlan()
        val result = plan.executePlan(blueprint, status)
        val expected = listOf(
            Pair(ExtractionStatus(1, 1, 1, 1, 0, 17, 0, 0), blueprint.geodeRobot)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `precedence plan test with no obsidian`() {
        val status = ExtractionStatus(1, 1, 1, 1, 3, 17, 0, 0)
        val plan = PrecedenceConstructionPlan()
        val result = plan.executePlan(blueprint, status)
        val expected = listOf(
            Pair(ExtractionStatus(1, 1, 1, 1, 0, 0, 0, 0), blueprint.obsidianRobot)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `precedence plan test with no clay`() {
        val status = ExtractionStatus(1, 1, 1, 1, 3, 0, 0, 0)
        val plan = PrecedenceConstructionPlan()
        val result = plan.executePlan(blueprint, status)
        val expected = listOf(
            Pair(ExtractionStatus(1, 1, 1, 1, 0, 0, 0, 0), blueprint.clayRobot)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `precedence plan test with not enough ore`() {
        val status = ExtractionStatus(1, 1, 1, 1, 2, 0, 0, 0)
        val plan = PrecedenceConstructionPlan()
        val result = plan.executePlan(blueprint, status)
        val expected = listOf(
            Pair(ExtractionStatus(1, 1, 1, 1, 0, 0, 0, 0), blueprint.oreRobot)
        )
        assertThat(result, `is`(expected))
    }

    @Test
    fun `precedence plan test with empty status`() {
        val status = ExtractionStatus()
        val plan = PrecedenceConstructionPlan()
        val result = plan.executePlan(blueprint, status)
        val expected = listOf(Pair(status, null))
        assertThat(result, `is`(expected))
    }

}