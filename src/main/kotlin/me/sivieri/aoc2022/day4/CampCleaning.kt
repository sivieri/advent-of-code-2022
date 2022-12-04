package me.sivieri.aoc2022.day4

class CampCleaning(input: List<String>) {

    private val ranges = input.map {
        val (part1, part2) = it.split(",", limit = 2)
        val (range1start, range1end) = part1.split("-", limit = 2)
        val (range2start, range2end) = part2.split("-", limit = 2)
        Pair(Range(range1start.toInt(), range1end.toInt()), Range(range2start.toInt(), range2end.toInt()))
    }

    fun countCompleteOverlapInPairs(): Int = ranges.count { (range1, range2) ->
        range1.fullyContains(range2) || range2.fullyContains(range1)
    }

}