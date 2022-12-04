package me.sivieri.aoc2022.day4

data class Range(
    val min: Int,
    val max: Int
) {

    fun fullyContains(other: Range): Boolean = min <= other.min && max >= other.max

    fun overlaps(other: Range): Boolean = (min..max).intersect(other.min..other.max).isNotEmpty()

}
