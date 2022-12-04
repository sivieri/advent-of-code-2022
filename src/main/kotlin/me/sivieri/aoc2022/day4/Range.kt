package me.sivieri.aoc2022.day4

data class Range(
    val min: Int,
    val max: Int
) {

    fun fullyContains(other: Range): Boolean = min <= other.min && max >= other.max

}
