package me.sivieri.aoc2022.day13

import me.sivieri.aoc2022.zipWithIndex

class ListsComparison(blocks: List<String>) {

    private val pairs = blocks.map {
        val (one, two) = it.trim().split("\n", limit = 2)
        Pair(ListsElement.parse(one), ListsElement.parse(two))
    }

    fun sumRightPairs(): Int = pairs
        .zipWithIndex { it + 1 }
        .filter { ListsElement.checkOrder(it.second.first, it.second.second) }
        .sumOf { it.first }

    fun divisorsOrder(): Int {
        val all = pairs
            .flatMap { listOf(it.first, it.second)}
            .plus(divider1)
            .plus(divider2)
            .sorted()
        val index1 = all.indexOf(divider1) + 1
        val index2 = all.indexOf(divider2) + 1
        return index1 * index2
    }

    companion object {
        private val divider1 = ListsElement.parse("[[2]]")
        private val divider2 = ListsElement.parse("[[6]]")
    }

}