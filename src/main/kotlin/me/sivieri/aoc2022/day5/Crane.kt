package me.sivieri.aoc2022.day5

import me.sivieri.aoc2022.tail
import java.util.ArrayDeque

class Crane(
    initialStatus: String,
    moves: String
) {

    val craneMoves: List<CraneMove> = moves
        .let {
            if (it.isNotBlank()) {
                it
                    .split("\n")
                    .map {
                        val parts = it.split(" ")
                        CraneMove(parts[1].toInt(), parts[3].toInt(), parts[5].toInt())
                    }
            }
            else emptyList()
        }
    val status: List<CraneStack> = run {
        val lines = initialStatus
            .split("\n")
            .reversed()
        val indexes = lines.first()
        val content = lines.tail()
        val size = indexes.split("   ").map { it.trim() }.size
        val stacks = (1..size).map { CraneStack(it, ArrayDeque()) }
        content.map {
            val parts = parseLineByThree(it)
            stacks.forEachIndexed { index, craneStack ->
                if (parts[index] != "-") craneStack.elements.push(parts[index].first())
            }
        }
        stacks
    }

    override fun toString(): String {
        val tempElements = status.map { CraneStack(it.index, it.elements.clone()) }
        val maxLength = tempElements.maxOf { it.elements.size } + 1
        return (1..maxLength)
            .map {
                if (it == 1) " ${(1..status.size).map { it }.joinToString("   ")} "
                else tempElements.map {
                    if (it.elements.peekLast() != null) "[${it.elements.removeLast()}]"
                    else "   "
                }.joinToString(" ")
            }
            .reversed()
            .joinToString("\n")
    }

    companion object {

        private fun parseLineByThree(line: String): List<String> {
            return line
                .chunked(4)
                .map {
                    if (it == "    " || it == "   ") "-"
                    else it.substring(1, 2)
                }
        }

    }

}