package me.sivieri.aoc2022.day5

import java.util.ArrayDeque

data class CraneStack(
    val index: Int,
    val elements: ArrayDeque<Char>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CraneStack

        if (index != other.index) return false
        if (elements.toList() != other.elements.toList()) return false

        return true
    }

    override fun hashCode(): Int {
        var result = index
        result = 31 * result + elements.toList().hashCode()
        return result
    }
}
