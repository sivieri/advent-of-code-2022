package me.sivieri.aoc2022.day5

import me.sivieri.aoc2022.tail
import java.util.ArrayDeque

class Crane(
    initialStatus: String,
    moves: String
) {

    private val craneMoves: List<CraneMove> = moves
        .let {
            if (it.isNotBlank()) {
                it
                    .split("\n")
                    .filter { it.isNotBlank() }
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
            .filter { it.isNotBlank() }
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

    fun move(move: CraneMove): Unit =
        (1..move.n).forEach { _ ->
            getStack(move.to).elements.push(getStack(move.from).elements.pop())
        }

    fun moveAll(debug: Boolean = false): Unit = craneMoves.forEach {
        move(it)
        if (debug) {
            println(toString())
            println()
        }
    }

    fun getTopStatus(): String = status.joinToString("") { it.elements.peek().toString() }

    private fun getStack(index: Int): CraneStack = status.find { it.index == index }!!

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