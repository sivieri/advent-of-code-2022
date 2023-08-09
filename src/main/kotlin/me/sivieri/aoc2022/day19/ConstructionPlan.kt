package me.sivieri.aoc2022.day19

interface ConstructionPlan {
    fun executePlan(
        blueprint: Blueprint,
        status: ExtractionStatus
    ): List<Pair<ExtractionStatus, Robot?>>
}

class BasicConstructionPlan: ConstructionPlan {
    override fun executePlan(blueprint: Blueprint, status: ExtractionStatus): List<Pair<ExtractionStatus, Robot?>> =
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
    override fun executePlan(blueprint: Blueprint, status: ExtractionStatus): List<Pair<ExtractionStatus, Robot?>> {
        if (blueprint.geodeRobot.isFeasible(status)) return listOf(Pair(blueprint.geodeRobot.updateStatusResources(status), blueprint.geodeRobot))
        if (blueprint.obsidianRobot.isFeasible(status)) return listOf(Pair(blueprint.obsidianRobot.updateStatusResources(status), blueprint.obsidianRobot))
        if (blueprint.clayRobot.isFeasible(status)) return listOf(Pair(blueprint.clayRobot.updateStatusResources(status), blueprint.clayRobot))
        if (blueprint.oreRobot.isFeasible(status)) return listOf(Pair(blueprint.oreRobot.updateStatusResources(status), blueprint.oreRobot))
        return listOf(Pair(status, null))
    }
}
