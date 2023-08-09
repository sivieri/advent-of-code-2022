@file:Suppress("ConvertArgumentToSet")

package me.sivieri.aoc2022.day19

interface ConstructionPlan {
    fun executePlan(
        blueprint: Blueprint,
        status: ExtractionStatus,
        minutesLeft: Int
    ): List<Pair<ExtractionStatus, Robot?>>
}

class BasicConstructionPlan: ConstructionPlan {
    override fun executePlan(
        blueprint: Blueprint,
        status: ExtractionStatus,
        minutesLeft: Int
    ): List<Pair<ExtractionStatus, Robot?>> =
        listOf(
            blueprint.oreRobot,
            blueprint.clayRobot,
            blueprint.obsidianRobot,
            blueprint.geodeRobot,
            null
        ).map { robot ->
            if (robot != null && robot.isFeasible(status)) Pair(robot.updateStatusResources(status), robot)
            else Pair(status, null)
        }.distinct()
}

class PrecedenceConstructionPlan: ConstructionPlan {
    override fun executePlan(
        blueprint: Blueprint,
        status: ExtractionStatus,
        minutesLeft: Int
    ): List<Pair<ExtractionStatus, Robot?>> {
        val robot = if (blueprint.geodeRobot.isFeasible(status))
            Pair(blueprint.geodeRobot.updateStatusResources(status), blueprint.geodeRobot)
        else if (blueprint.obsidianRobot.isFeasible(status))
            Pair(blueprint.obsidianRobot.updateStatusResources(status), blueprint.obsidianRobot)
        else if (blueprint.clayRobot.isFeasible(status))
            Pair(blueprint.clayRobot.updateStatusResources(status), blueprint.clayRobot)
        else if (blueprint.oreRobot.isFeasible(status))
            Pair(blueprint.oreRobot.updateStatusResources(status), blueprint.oreRobot)
        else Pair(status, null)
        return listOf(
            Pair(status, null),
            robot
        ).distinct()
    }
}

class ResourceOrientedConstructionPlan: ConstructionPlan {
    override fun executePlan(
        blueprint: Blueprint,
        status: ExtractionStatus,
        minutesLeft: Int
    ): List<Pair<ExtractionStatus, Robot?>> {
        // do not calculate robots that already are present in the max number required
        val robots = mutableListOf(
            blueprint.oreRobot,
            blueprint.clayRobot,
            blueprint.obsidianRobot,
            blueprint.geodeRobot
        )
        val maxOre = robots.maxOf { it.ore() }
        val maxClay = robots.maxOf { it.clay() }
        val maxObsidian = robots.maxOf { it.obsidian() }
        if (status.oreRobots >= maxOre) robots.remove(blueprint.oreRobot)
        if (status.clayRobots >= maxClay) robots.remove(blueprint.clayRobot)
        if (status.obsidianRobots >= maxObsidian) robots.remove(blueprint.obsidianRobot)

        // do not calculate robots in perspective
        if (status.oreRobots * minutesLeft + status.ore >= maxOre * minutesLeft) robots.remove(blueprint.oreRobot)
        if (status.clayRobots * minutesLeft + status.clay >= maxClay * minutesLeft) robots.remove(blueprint.clayRobot)
        if (status.obsidianRobots * minutesLeft + status.obsidian >= maxObsidian * minutesLeft) robots.remove(blueprint.obsidianRobot)

        // do not calculate robots with timing
        if (minutesLeft == 0) return listOf(Pair(status, null))
        if (minutesLeft == 1 && blueprint.geodeRobot.isFeasible(status)) return listOf(Pair(status.copy(geode = status.geode + 1), null))
        if (minutesLeft == 1) return listOf(Pair(status, null))

        return if (blueprint.geodeRobot.isFeasible(status)) {
            // if we can build a geode robot, we do it
            listOf(Pair(blueprint.geodeRobot.updateStatusResources(status), blueprint.geodeRobot))
        }
        else {
            // get all remaining combinations
            robots
                .toList()
                .plus(null)
                .map { robot ->
                    if (
                        robot != null &&
                        robot.isFeasible(status)
                    ) Pair(robot.updateStatusResources(status), robot)
                    else Pair(status, null)
                }.distinct()
        }
    }
}
