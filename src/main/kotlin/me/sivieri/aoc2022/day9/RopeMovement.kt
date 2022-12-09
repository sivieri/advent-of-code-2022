package me.sivieri.aoc2022.day9

data class RopeMovement(
    val ropeDirection: RopeDirection,
    val steps: Int
) {

    companion object {
        fun parseLine(line: String): RopeMovement {
            val (dir, steps) = line.split(" ", limit = 2)
            return RopeMovement(RopeDirection.getRopeDirection(dir.first()), steps.toInt())
        }
    }

}
