package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.common.Left
import me.sivieri.aoc2022.common.Right
import me.sivieri.aoc2022.zipWithIndex
import kotlin.math.max

class ListsComparison(blocks: List<String>) {

    private val pairs = blocks.map {
        val (one, two) = it.trim().split("\n", limit = 2)
        Pair(ListsElement.parse(one), ListsElement.parse(two))
    }

    fun sumRightPairs(): Int = pairs
        .zipWithIndex { it + 1 }
        .filter { checkOrder(it.second.first, it.second.second) }
        .sumOf { it.first }

    companion object {
        fun checkOrder(a: ListsElement, b: ListsElement): Boolean = checkOrder0(a, b)!!

        private fun checkOrder0(a: ListsElement, b: ListsElement): Boolean? {
            val max = max(a.elements.size, b.elements.size)
            (0 until max).forEach { i ->
                if (a.elements.size <= i) return true
                else if (b.elements.size <= i) return false
                else {
                    val a1 = a.elements[i]!!
                    val b1 = b.elements[i]!!
                    when {
                        a1 is Left && b1 is Left -> {
                            if (a1.value < b1.value) return true
                            else if (a1.value > b1.value) return false
                        }
                        a1 is Right && b1 is Right -> {
                            val v = checkOrder(a1.value, b1.value)
                            if (v != null) return v
                        }
                        a1 is Left && b1 is Right -> {
                            val v = checkOrder(ListsElement(mutableMapOf(0 to a1)), b1.value)
                            if (v != null) return v
                        }
                        a1 is Right && b1 is Left -> {
                            val v = checkOrder(a1.value, ListsElement(mutableMapOf(0 to b1)))
                            if (v != null) return v
                        }
                    }
                }
            }
            return null
        }
    }


}