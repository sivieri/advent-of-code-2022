package me.sivieri.aoc2022.day7

data class File(
    val name: String,
    val size: Int
) {
    override fun toString(): String {
        return "- $name (file, size=$size)"
    }
}
